package utils;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import dev.lavalink.youtube.YoutubeAudioSourceManager; // <-- nuovo source YouTube v2
import net.dv8tion.jda.api.entities.Guild;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton: tiene l'AudioPlayerManager e una mappa guild->manager.
 * Configurato per Lavaplayer 2.x con il NUOVO YouTube source (dev.lavalink.youtube).
 */

public class PlayerManager {
    private static PlayerManager INSTANCE;

    private final AudioPlayerManager playerManager;
    private final Map<Long, GuildMusicManager> musicManagers = new ConcurrentHashMap<>();

    private PlayerManager() {
        this.playerManager = new DefaultAudioPlayerManager();

        // Registra sorgenti remote/locali MA escludi la vecchia YouTube integrata (deprecata in LP 2.x)
        AudioSourceManagers.registerRemoteSources(
                playerManager,
                com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager.class // exclude old YT
        );
        AudioSourceManagers.registerLocalSource(playerManager);

        // Registra il nuovo YouTube source v2 (consigliato per LP 2.x)
        // allowSearch = true -> abilita "ytsearch:" e "ytmsearch:"
        playerManager.registerSourceManager(new YoutubeAudioSourceManager(true));
    }

    public static synchronized PlayerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }

    // Ottiene/crea il manager per la guild e aggancia l'AudioSendHandler a JDA.
    public GuildMusicManager getGuildMusicManager(Guild guild) {
        return musicManagers.computeIfAbsent(guild.getIdLong(), id -> {
            GuildMusicManager mgr = new GuildMusicManager(playerManager);
            guild.getAudioManager().setSendingHandler(mgr.getSendHandler());
            return mgr;
        });
    }

    // Carica e mette in coda (o parte subito) una URL o una ricerca (es. "ytsearch:...").
    public void loadAndPlay(Guild guild, String identifier) {
        GuildMusicManager musicManager = getGuildMusicManager(guild);

        playerManager.loadItemOrdered(musicManager, identifier, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                musicManager.scheduler.queue(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack selected = playlist.getSelectedTrack();
                if (selected == null && !playlist.getTracks().isEmpty()) {
                    selected = playlist.getTracks().get(0);
                }
                if (selected != null) {
                    musicManager.scheduler.queue(selected);
                }
            }

            @Override
            public void noMatches() {
                System.out.println("[PlayerManager] No track found for: " + identifier);
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                System.err.println("[PlayerManager] Load error: " + exception.getMessage());
            }
        });
    }

    // Ferma il brano corrente e svuota la coda.
    public void stop(Guild guild) {
        GuildMusicManager mgr = musicManagers.get(guild.getIdLong());
        if (mgr != null) {
            mgr.player.stopTrack();
            mgr.scheduler.clearQueue();
        }
    }

    // Salta al prossimo brano, se presente.
    public void skip(Guild guild) {
        GuildMusicManager mgr = musicManagers.get(guild.getIdLong());
        if (mgr != null) {
            mgr.scheduler.nextTrack();
        }
    }
}


