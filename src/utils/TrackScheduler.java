package utils;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

 // Gestisce la coda e il passaggio automatico al brano successivo.
 
public class TrackScheduler extends AudioEventAdapter {
    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue = new LinkedBlockingQueue<>();
    private volatile boolean repeating = false;

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
    }

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

    public boolean isRepeating() {
        return repeating;
    }

    // Aggiunge un brano: se nulla sta suonando, parte subito; altrimenti va in coda.
    public void queue(AudioTrack track) {
        if (!player.startTrack(track, true)) {
            queue.offer(track);
        }
    }

    // Salta al prossimo brano in coda (se presente).
    public void nextTrack() {
        player.startTrack(queue.poll(), false);
    }

    // Svuota la coda.
    public void clearQueue() {
        queue.clear();
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            if (repeating && track != null) {
                // clona e riparte lo stesso brano
                player.startTrack(track.makeClone(), false);
                return;
            }
            nextTrack();
        }
    }

    @Override
    public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception) {
        // in caso di errore, prova a passare al prossimo
        nextTrack();
    }
}

