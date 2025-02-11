# Briscola Android App

## Descrizione

Questa applicazione Android implementa il classico gioco di carte italiano "Briscola". Il progetto nasce con l'obiettivo di creare un'esperienza di gioco coinvolgente e fedele alle regole tradizionali, sfruttando le potenzialità offerte dalle moderne tecnologie mobile.

## Funzionalità principali

- Modalità **Giocatore Singolo** con intelligenza artificiale
- Modalità **Multiplayer Online** con matchmaking automatico
- **Interfaccia Utente Intuitiva** basata su Material Design
- **Animazioni fluide** e adattabilità a diverse dimensioni di schermo
- **Gestione API REST** con Spring Boot per la comunicazione tra client e server

## Tecnologie utilizzate

- **Android Studio** per lo sviluppo dell'applicazione
- **Java** per la logica di gioco
- **XML** per il design dell'interfaccia utente
- **Volley** per la gestione delle richieste HTTP
- **Spring Boot** per la gestione del backend e delle API
- **Emulatore AVD** per il testing su diversi dispositivi Android

## Struttura del Progetto

L'app segue l'architettura **Model-View-Controller (MVC)** e si divide nei seguenti moduli:

- **Modulo di Logica:** Gestione delle regole del gioco, assegnazione punteggi e gestione del mazzo
- **Modulo UI:** Implementazione dell'interfaccia utente con supporto per diversi formati di schermo
- **Modulo Multiplayer:** Implementazione della comunicazione tra client e server per le partite online

## Installazione

1. Clonare il repository:
   ```sh
   git clone https://github.com/TYaroslavit/AppBriscolaAndroid.git
   ```
2. Aprire il progetto con **Android Studio**
3. Configurare l'emulatore AVD o collegare un dispositivo fisico
4. Avviare l'applicazione

## API e Backend

Le funzionalità multiplayer si basano su un backend sviluppato con **Spring Boot**, che gestisce il matchmaking, le mosse dei giocatori e la distribuzione delle carte. Le API principali includono:

- `POST /ready` - Gestione della coda di attesa per le partite
- `GET /getPartita` - Recupero dei dati della partita
- `POST /updateData` - Registrazione delle mosse dei giocatori
- `GET /getAnyUpdate` - Aggiornamento in tempo reale dello stato della partita

## Test e Prestazioni

L'app è stata sottoposta a:

- **Test Funzionali** per garantire il corretto funzionamento delle regole di gioco
- **Test di Usabilità** con utenti per ottimizzare l'interfaccia
- **Test di Prestazioni** per garantire fluidità anche su dispositivi meno performanti

## Sviluppi Futuri

- **Versione iOS** dell'applicazione
- **Personalizzazione** dei mazzi di carte con temi diversi
- **Miglioramento dell'IA** per un'esperienza più realistica in modalità giocatore singolo

## Autore

- **Tura Yaroslav** - Università degli Studi di Salerno

## Licenza

Questo progetto è distribuito sotto licenza MIT.

