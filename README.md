### Teknolojiler ve Kütüphaneler

- **Java 11**
- **Maven**
- **Selenium WebDriver**
- **TestNG**
- **Rest Assured**
- **WebDriverManager**
- **Log4j2**
- **Jackson**

### Tarayıcı Desteği

- Chrome
- Firefox

Tarayıcı seçimi `testng.xml` dosyasından yapılabilir.

### Proje Yapısı

```
src/
├── main/
│   ├── java/
│   │   ├── base/              # Temel test ve step sınıfları
│   │   │   ├── BaseTest.java
│   │   │   └── BaseStep.java
│   │   ├── driver/            # WebDriver yönetimi
│   │   │   └── DriverFactory.java
│   │   ├── pages/             # Page Object Model sınıfları
│   │   │   ├── HomePage.java
│   │   │   ├── ProductDetailPage.java
│   │   │   └── BasketPage.java
│   │   └── utils/             # Yardımcı sınıflar
│   │       ├── JsonReader.java
│   │       └── WaitUtil.java
│   └── resources/
│       ├── log4j2.xml         # Log4j2 konfigürasyonu
│       └── searchText.json    # Test verileri
└── test/
    └── java/
        ├── apitestcases/      # API test senaryoları
        │   └── API_TrelloServiceTests.java
        └── uitestcases/       # UI test senaryoları
            └── UI_HepsiburadaTests.java
```

## Kurulum ve Çalıştırma

### Gereksinimler

- Java 11 veya üzeri
- Maven 3.6 veya üzeri
- Chrome veya Firefox tarayıcısı

### Adımlar

1. **Projeyi klonlayın veya indirin**

2. **Bağımlılıkları yükleyin**
   ```bash
   mvn clean install
   ```

3. **Testleri çalıştırın**

   Tüm testleri çalıştırmak için:
   ```bash
   mvn test
   ```

   Sadece UI testlerini çalıştırmak için:
   ```bash
   mvn test -Dtest=UI_HepsiburadaTests
   ```

   Sadece API testlerini çalıştırmak için:
   ```bash
   mvn test -Dtest=API_TrelloServiceTests
   ```

4. **TestNG XML ile çalıştırma**

   `testng.xml` dosyasından tarayıcı seçimi yaparak testleri çalıştırabilirsiniz:
   ```bash
   mvn test
   ```

### Test Verileri

Test verileri `src/main/resources/searchText.json` dosyasından okunmaktadır. Bu dosyada arama yapılacak ürün anahtar
kelimesi tanımlanmıştır.

```json
{
  "keyword": "iphone"
}
```

## 📝 Test Senaryoları

### UI Test Senaryoları

1. **case1_Check_Product_Reviews**
    - Popüler bir ürünü arama
    - Ürün detay sayfasına gitme
    - Yorumlar sekmesini açma ve en yeni yorumlara göre sıralama
    - Yorumları ve puanları kontrol etme

2. **case2_Check_Other_Seller_Price_List**
    - Popüler bir ürünü arama
    - Ürün detay sayfasında diğer satıcılar bölümünü kontrol etme
    - En düşük fiyatlı ürünü bulma ve sepete ekleme

3. **case3_Check_Compare_Price_Between_Product_Detail_And_Basket**
    - Ürün detay sayfasındaki fiyatı alma
    - Ürünü sepete ekleme ve sepet sayfasına gitme
    - Sepet sayfasındaki fiyatı kontrol etme ve karşılaştırma

### API Test Senaryoları

1. **createBoard**: Yeni bir Trello board'u oluşturma
2. **createList**: Board içinde yeni bir liste oluşturma
3. **createCard**: List içinde yeni bir kart oluşturma
4. **createCardTwo**: İkinci bir kart oluşturma
5. **updateCart**: Kart bilgilerini güncelleme
6. **deleteCard**: Kart silme işlemleri
7. **deleteCard2**: İkinci kartı silme
8. **deleteBoard**: Board'u silme

## TestNG Raporları

TestNG, test çalıştırmaları sonrasında detaylı HTML raporları oluşturur. Raporlar aşağıdaki konumda bulunur:

**Rapor Konumu**: `target/surefire-reports/index.html`

Test çalıştırması tamamlandıktan sonra, raporu görüntülemek için:

1. Proje kök dizininde `target/surefire-reports` klasörüne gidin
2. `index.html` dosyasını tarayıcınızda açın

## Loglama

Proje Log4j2 kullanarak detaylı loglama yapmaktadır. Log dosyaları `logs/test.log` dizininde saklanır.

## Konfigürasyon

### TestNG Konfigürasyonu

`testng.xml` dosyasından tarayıcı seçimi ve test suite konfigürasyonu yapılabilir:

```xml

<parameter name="browser" value="firefox"/>
```
