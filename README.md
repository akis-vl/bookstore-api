
# Bookstore API Automation Tests (Java 21 + RestAssured + TestNG + Allure + GitHub Actions)

This project automates testing of the **Bookstore API** using:
- **Java 21**
- **Maven**
- **RestAssured**
- **TestNG**
- **Faker**
- **Allure Reports**
- **GitHub Actions CI/CD** (with automatic Allure publishing)

---

## 📁 Project Structure

```
bookstore-api/
├── .gitignore
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       └── com/bookstore/
    │           ├── api/
    │           │   ├── AuthorClient.java
    │           │   ├── BaseClient.java
    │           │   └── BookClient.java
    │           ├── config/
    │           │   └── TestConfig.java
    │           └── models/
    │               ├── Author.java
    │               └── Book.java
    └── test/
        └── java/
            └── com/bookstore/
                ├── base/
                │   └── BaseTest.java
                ├── books/
                │   ├── CrudBooksTests.java
                │   ├── GetBooksTests.java
                │   └── NegativeBooksTests.java
                └── data/
                    └── TestData.java
        └── resources/
            ├── schemas/
            │   ├── author.json
            │   ├── authors-array.json
            │   ├── book.json
            │   └── books-array.json
            └── suites/
                └── testng.xml
```

---

## 🚀 Prerequisites

You need:

| Tool | Version |
|------|----------|
| Java | **21** |
| Maven | **3.9+** |
| Git | Latest |

---

## 🔧 How to Set Up the Project

### 1. Clone the Repository
```
git clone git@github.com:akis-vl/bookstore-api.git
cd bookstore-api
```

### 2. Build and Run Tests Locally
```
mvn clean test
```

After running, Allure results appear in:
```
target/allure-results/
```

---

## 📊 Generate Allure Report Locally

If you have Allure installed:

```
allure serve target/allure-results
```

Or using Maven plugin:

```
mvn allure:report
```

Report will be under:

```
target/site/allure-maven-plugin/index.html
```

---

## 🧪 Running Tests from IntelliJ IDEA

1. Open project in IntelliJ
2. Ensure **Project SDK = Java 21**
3. Reload Maven (🧹 icon)
4. Right-click `testng.xml` → **Run**

---

## 🛠 Configuration

### 🔧 API Base URL
Set inside the API client base class:
```
https://fakerestapi.azurewebsites.net/api/v1
```

---

## 📚 JSON Schema Validation

Schemas live in:
```
src/test/resources/schemas/
```

Tests use:
```java
matchesJsonSchemaInClasspath("schemas/book.json")
```

---

## 🚀 CI/CD Pipeline (GitHub Actions)

The pipeline:

1. Runs tests with Java 21
2. Generates Allure report
3. Uploads report as an artifact
4. Publishes Allure report to GitHub Pages
5. Adds a clickable summary link

Workflow file is in:

```
.github/workflows/ci.yml
```

Once GitHub Pages is enabled, your report will be available at:

```
https://akis-vl.github.io/bookstore-api/
```

---

## ❗ Always Publish Allure Reports (Even on Failures)

Pipeline uses:
- `continue-on-error: true` for Maven tests
- `if: always()` for Allure generation and publishing

So failed tests still produce full reports.

---

## 🧹 Clean Project

```
mvn clean
```