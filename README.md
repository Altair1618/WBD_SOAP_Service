# Milestone 2 WBD Kelompok 9 [SOAP SERVICE]

## Deskripsi Aplikasi
Aplikasi LearnIt! adalah sebuah aplikasi LMS yang sisusun untuk memenuhi Tugas Milestone 2 IF3110 Pengembangan Aplikasi Berbasis Web. Aplikasi dapat diakses dengan 3 tipe pengguna: Admin, Pengajar, Pelajar. Admin memiliki kemampuan untuk mengubah semua data. Pengajar bisa membuat, membaca, mengubah, dan menghapus mata kuliah, modul, dan materi. Pelajar dapat mendaftar suatu mata kuliah dan membaca modul serta materi. <br><br>
Aplikasi ini memiliki mode premium dimana pelajar harus melakukan langganan terlebih dahulu dan di-approve oleh Admin. Pengajar yang ingin membuat mata kuliah premium harus melakukan apply yang harus di-approve oleh Admin. Kelebihan dari mata kuliah premium adalah adanya sertifikat penyelesaian yang dapat dikirimkan oleh pengajar mata kuliah berkait. <br><br>
SOAP Service ini merupakan service yang mengurus bagian langganan.

## Dibuat Oleh:
- [Moch. Sofyan Firdaus (13521083)](https://github.com/msfir)
- [Farhan Nabil Suryono (13521114)](https://github.com/Altair1618)

## Tech Stacks:
1. Java & Maven 8 Amazon Corretto
2. MySQL Database
3. Jax-WS

## Cara Instalasi
1. Clone _repository_ ini beserta repository lainnya dalam satu folder yang sama
2. Buatlah sebuah file `.env` yang bersesuaian dengan penggunaan (contoh file tersebut dapat dilihat pada `.env.example`).

## Cara Menjalankan Server
1. Jalankan perintah `docker compose up -d` pada folder repository config
2. Aplikasi berjalan pada `http://localhost:9000`.
3. Hentikan aplikasi dengan perintah `docker compose down` pada folder repository config.

## Endpoints
<table>
  <thead>
    <tr>
      <td>
        Service
      </td>
      <td>
        Nama Fungsi
      </td>
      <td>
        Deskripsi
      </td>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        LoggingService
      </td>
      <td>
        getAllLoggings()
      </td>
      <td>
        Mendapatkan semua log pada service
      </td>
    </tr>
    <tr>
      <td>
        SubscriptionService
      </td>
      <td>
        createSubscriptionRequest(userId)
      </td>
      <td>
        Membuat request langganan berstatus pending berdasarkan userId
      </td>
    </tr>
    <tr>
      <td>
        SubscriptionService
      </td>
      <td>
        getAllSubscriptionRequests()
      </td>
      <td>
        Mendapatkan semua request langganan yang masih pending
      </td>
    </tr>
    <tr>
      <td>
        SubscriptionService
      </td>
      <td>
        getUserSubscriptionStatus(userId)
      </td>
      <td>
        Mendapatkan semua status terbaru pengguna
      </td>
    </tr>
    <tr>
      <td>
        SubscriptionService
      </td>
      <td>
        acceptSubscriptionRequest(id)
      </td>
      <td>
        Menerima request langganan pengguna berdasarkan id
      </td>
    </tr>
    <tr>
      <td>
        SubscriptionService
      </td>
      <td>
        rejectSubscriptionRequest(id)
      </td>
      <td>
        Menolak request langganan pengguna berdasarkan id
      </td>
    </tr>
  </tbody>
</table>

## Pembagian Tugas
### Setup
<table>
  <tbody>
    <tr>
      <td>Docker</td>
      <td>13521114</td>
    </tr>
    <tr>
      <td>Framework Pengerjaan</td>
      <td>13521114</td>
    </tr>
    <tr>
      <td>Database</td>
      <td>13521114</td>
    </tr>
  </tbody>
</table>

### Service
<table>
  <tbody>
    <tr>
      <td>getAllLoggings</td>
      <td>13521114</td>
    </tr>
    <tr>
      <td>createSubscriptionRequest</td>
      <td>13521114</td>
    </tr>
    <tr>
      <td>getAllSubscriptionRequests</td>
      <td>13521114</td>
    </tr>
    <tr>
      <td>getUserSubscriptionStatus</td>
      <td>13521114</td>
    </tr>
    <tr>
      <td>acceptSubscriptionRequest</td>
      <td>13521114</td>
    </tr>
    <tr>
      <td>rejectSubscriptionRequest</td>
      <td>13521114</td>
    </tr>
  </tbody>
</table>
