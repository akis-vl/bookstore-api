
# Bookstore API Automation Tests

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
└── src/
    ├── main/
    │   └── java/
    │       └── com/bookstore/
    │           ├── api/
    │           ├── config/
    │           ├── models/
    │           └── utils/
    └── test/
        ├── java/
        │   └── com/bookstore/
        │       ├── authors/
        │       ├── base/
        │       ├── books/
        │       └── data/
        └── resources/
            ├── schemas/
            └── suites/
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
git clone https://github.com/akis-vl/bookstore-api.git
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

## 🚀 CI/CD Pipeline (GitHub Actions)

The pipeline:

1. Automatically upon push, runs tests that are tagged with "smoke"
2. Manually, the user can select one of the tags: "regression" or "smoke"
3. Generates Allure report
4. Uploads report as an artifact
5. Publishes Allure report to GitHub Pages
6. Adds a clickable summary link

Workflow file is in:

```
.github/workflows/ci.yml
```

The report is available at:

```
https://akis-vl.github.io/bookstore-api/
```

---

## ❗ Always Publish Allure Reports (Even on Failures)

Pipeline uses:
- `continue-on-error: true` for Maven tests
- `if: always()` for Allure generation and publishing

So failed tests still produce full reports.
