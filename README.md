# 🎬 Kasih Review - Mobile App

> **Aplikasi Review Film Berbasis Android**
> Project ini merupakan bagian *front-end mobile* untuk ekosistem Kasih Review, dibangun menggunakan Kotlin dan Android Jetpack.

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple?style=for-the-badge&logo=kotlin)
![Android](https://img.shields.io/badge/Platform-Android-green?style=for-the-badge&logo=android)
![Gradle](https://img.shields.io/badge/Build-Gradle-blue?style=for-the-badge&logo=gradle)

## 📱 Tentang Aplikasi
**KasihReview** adalah aplikasi review film berbasis platform mobile Android. Aplikasi ini memungkinkan pengguna untuk:
* 🔍 Mencari film-film favorit mereka.
* ⭐ Meninggalkan rating dan ulasan (review) untuk film tersebut.
* 📝 Mengelola ulasan yang telah dibuat.

Aplikasi ini dirancang sebagai *client-side* yang berinteraksi dengan layanan Backend untuk menampilkan data film dan ulasan secara *real-time*.

## 🛠️ Tech Stack
* **Language:** [Kotlin](https://kotlinlang.org/) (100%)
* **Build System:** Gradle (Kotlin DSL - `build.gradle.kts`)
* **Architecture:** MVVM (Model-View-ViewModel) *[Disarankan]*
* **Networking:** Retrofit *[Asumsi umum untuk connect ke API]*
* **UI Components:** XML / Jetpack Compose

## 📂 Struktur Project
* `/app` - Source code utama aplikasi Android (Activity, Fragment, Layouts).
* `/gradle` - Wrapper dan konfigurasi Gradle.
* `build.gradle.kts` - Konfigurasi dependensi project menggunakan Kotlin DSL.

## 🚀 Cara Menjalankan (Getting Started)

### Prasyarat
* **Android Studio** (Versi terbaru, misal: Hedgehog/Iguana).
* **JDK 17** atau yang lebih baru.

### Langkah Instalasi
1.  **Clone Repository**
    ```bash
    git clone [https://github.com/Kennata/KasihReview.git](https://github.com/Kennata/KasihReview.git)
    ```
2.  **Buka di Android Studio**
    * Buka Android Studio -> `File` -> `Open` -> Pilih folder `KasihReview`.
3.  **Sync Gradle**
    * Tunggu hingga proses *Gradle Build* selesai (biasanya memakan waktu beberapa menit saat pertama kali).
4.  **Konfigurasi API (Opsional)**
    * Jika aplikasi butuh koneksi ke Backend lokal, pastikan IP address di konfigurasi Retrofit sudah sesuai dengan IP laptop/backend kamu.
5.  **Run Application**
    * Sambungkan HP Android via USB atau gunakan Emulator.
    * Klik tombol **Run (▶)** di toolbar atas.

## 👥 Kontributor
Project ini dikembangkan oleh tim **Kasih Review**:
* **Kennata** -
* **Faisal Surya Saputra** -
* **Faza Farih Riyadi** -
* **Dzaky Renaldy** -
* **Muhammad Ravi Athallaa** - 

---
*Dibuat untuk memenuhi Tugas Besar Mata Kuliah - Telkom University.*
