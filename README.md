# MusicByte 🎵🤖

MusicByte is a lightweight Discord **music bot** built in **Java** using the [JDA](https://github.com/DV8FromTheWorld/JDA) library and [LavaPlayer](https://github.com/sedmelluq/lavaplayer) for audio playback.  
It supports modern **slash commands** and provides reliable music features such as queues, skipping, pausing/resuming, and YouTube/Spotify search.

> **Project status:** 🟢 **Stable** • 🎶 **Actively maintained** — Designed as a practical learning project that want to evolve into a full-featured music bot.  
> You can contact me for help, feature requests, or troubleshooting.


> ℹ️ Useful note: the LavaPlayer dependency used in this project was taken from the [official Maven repository (lavaplayer-2.2.4.pom)](https://repo1.maven.org/maven2/dev/arbjerg/lavaplayer/2.2.4/lavaplayer-2.2.4.pom).


---

## ✨ Features

- **Join/Leave voice channels** → `/join`, `/leave`  
- **Play music from YouTube/Spotify** → `/play <url | search>`  
- **Queue system** → Add multiple tracks or playlists  
- **Playback controls** → `/pause`, `/resume`, `/skip`, `/stop`  
- **Now playing** → Shows the current track  
- **Per-guild music manager** → Isolated queue & player per server  

---

## 📜 Command format

- ``<arg>`` = required argument → the command will not work without it.  
- ``[arg]`` = optional argument → can be omitted; the command will use a default value if not provided.  

---

## 📜 Commands

- ``/join`` → Connects the bot to your current voice channel  
- ``/leave`` → Disconnects the bot from the voice channel  
- ``/play <query>`` → Plays a track or playlist by URL or YouTube search  
- ``/pause`` → Pauses the current playback  
- ``/resume`` → Resumes playback if paused  
- ``/skip`` → Skips the current track  
- ``/stop`` → Stops playback and clears the queue  
- ``/queue`` → Shows the current queue  
- ``/nowplaying`` → Displays the track that is currently playing  

---

## 🚀 Getting Started

### Prerequisites
- Java **21** or newer  
- [Maven](https://maven.apache.org/)  
- A Discord bot token (from the [Discord Developer Portal](https://discord.com/developers/applications))  

### Setup

1. **Clone the repository**  

2. Open the project in your favorite IDE (e.g. Eclipse or IntelliJ).  

3. Replace the placeholder with your actual Discord bot token inside `Main.java`:  
   JDABuilder.createDefault("YOUR_DISCORD_BOT_TOKEN")

4. Run the bot by launching the Main class.

## License

MusicByte © All rights reserved.  
This project is provided for personal and educational use only.  
Commercial use, redistribution, or deploying this bot on public/community servers without explicit permission is strictly prohibited.

---


## 🎯 Italiano

MusicByte è un bot musicale per Discord scritto in **Java** con la libreria [JDA](https://github.com/DV8FromTheWorld/JDA) e [LavaPlayer](https://github.com/sedmelluq/lavaplayer) per la riproduzione audio.  
Supporta i moderni **slash commands** e offre funzionalità musicali affidabili come code, skip, pausa/ripresa e ricerca su YouTube/Spotify.

> **Stato progetto:** 🟢 **Stabile** • 🎶 **In sviluppo attivo** — Nato come progetto di apprendimento, si è evoluto in un bot musicale completo e pronto all’uso.
> Contattami per richieste, nuove funzionalità o assistenza.

---

### ✨ Funzionalità

- **Entra/esce dai canali vocali** → `/join`, `/leave`  
- **Riproduzione musicale da YouTube/Spotify** → `/play <url | ricerca>`  
- **Supporto link Spotify** (track, album, playlist) → risolti su YouTube  
- **Gestione coda** → Aggiunta multipla di tracce e playlist  
- **Controlli di riproduzione** → `/pause`, `/resume`, `/skip`, `/stop`  
- **Traccia attuale** → `/nowplaying`  
- **Gestione indipendente per server** → Ogni server ha la sua coda  

---

### 📜 Comandi

- ``/join`` → Fa entrare il bot nel tuo canale vocale  
- ``/leave`` → Fa uscire il bot dal canale vocale  
- ``/play <query>`` → Riproduce un brano o una playlist (URL o ricerca YouTube)  
- ``/pause`` → Mette in pausa la riproduzione  
- ``/resume`` → Riprende la riproduzione  
- ``/skip`` → Salta il brano corrente  
- ``/stop`` → Ferma la riproduzione e svuota la coda  
- ``/queue`` → Mostra la coda attuale  
- ``/nowplaying`` → Mostra il brano in riproduzione  

---

### 🚀 Esecuzione

1. **Clona il repository**  

2. Apri il progetto nel tuo ide preferito.

3. Inserisci il tuo **token Discord** nel file `Main.java`:
   JDABuilder.createDefault("IL_TUO_TOKEN")

4. Avvia il bot eseguendo la classe Main.