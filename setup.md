
# ⏰ Countdown Timer Web App

A full-stack **Countdown Timer** web application that allows users to:

-   Set a **counter name** and **target date/time**.
    
-   Save the timer to a **backend** (Spring Boot + MongoDB Atlas).
    
-   Display a **live countdown** (days, hours, minutes, seconds).
    
-   **Update** or **reset** the countdown anytime.
    
-   Show clear **success/error feedback**.
    
-   Fully **responsive** UI for desktop and mobile devices.
    

----------

## 🧱 Project Structure

```
countdown-timer/
├── backend/     → Spring Boot (Java 17)
└── frontend/    → React + Vite + Axios

```

----------

## 🚀 Tech Stack

Frontend - React, Vite, Axios, HTML5, CSS3

Backend - Spring Boot (Web, Validation, Data MongoDB)

Database - MongoDB Atlas

Language - Java 17, JavaScript (ES6)

----------

## ⚙️ 1. Backend Setup (Spring Boot)

### 🧩 Prerequisites

-   Java **17+**
    
-   Maven **3.8+**
    
-   MongoDB Atlas account → [https://www.mongodb.com/cloud/atlas](https://www.mongodb.com/cloud/atlas)
    

----------

### 🪣 Step 1: Setup MongoDB Atlas

1.  Create a **Free Cluster** in MongoDB Atlas.
    
2.  Go to **Database Access** → Add New Database User (create username & password).
    
3.  Go to **Network Access** → Allow Access from Anywhere (`0.0.0.0/0`).
    
4.  Copy your connection string (Driver → Java):
    
    ```
    mongodb+srv://<username>:<password>@<cluster>.mongodb.net/?retryWrites=true&w=majority
    
    ```
    

----------

### ⚙️ Step 2: Configure Backend

#### 📁 Project Structure

```
backend/
├── src/main/java/com/example/countdown/
│   ├── CountdownApplication.java
│   ├── controller/TimerController.java
│   ├── service/TimerService.java
│   ├── repository/TimerRepository.java
│   ├── model/Timer.java
│   └── exception/
│       ├── GlobalExceptionHandler.java
│       └── ResourceNotFoundException.java
└── src/main/resources/application.properties

```

#### ⚙️ application.properties

```properties
spring.application.name=countdown-backend
server.port=8080

# MongoDB Atlas Connection
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/timerdb?retryWrites=true&w=majority

# Enable Mongo auditing
spring.data.mongodb.auto-index-creation=true

# Logging
logging.level.org.springframework.data.mongodb.core.MongoTemplate=INFO

```

----------

### ▶️ Step 3: Run Backend

```bash
cd backend
mvn spring-boot:run

```

If successful, the console should display:

```
Tomcat started on port(s): 8080
Started CountdownApplication in 3.5 seconds

```

----------

## 💻 2. Frontend Setup (React + Vite)

### 🧩 Prerequisites

-   Node.js **18+**
    
-   npm or yarn
    

----------

### ⚙️ Step 1: Setup Frontend

```bash
cd frontend
npm install

```

This installs all dependencies including Axios.

----------

### ⚙️ Step 2: Configure Backend URL

In `frontend/src/services/api.js`, verify the backend URL:

```javascript
const API_BASE = "http://localhost:8080/api/timer";

```

----------

### ▶️ Step 3: Run Frontend

```bash
npm run dev

```

Then open the provided link in your browser (typically):  
👉 [http://localhost:5173](http://localhost:5173/)

----------

## 🧠 Features

Feature

Description

🕐 **Set Timer**

Input counter name and future target date/time, then start countdown.

✏️ **Update Timer**

Edit name or target date/time.

🔁 **Reset Timer**

Delete/reset the timer.

💾 **Persistence**

Saves data in MongoDB Atlas.

⏳ **Live Countdown**

Real-time countdown (days, hours, minutes, seconds).

📱 **Responsive Design**

Works smoothly on both desktop and mobile.

✅ **Validation**

Name (2–32 chars) and future date/time required.

⚠️ **Error Handling**

Displays clear messages for validation or API issues.

----------

## 🔌 API Endpoints

Method

Endpoint

Description

**POST**

`/api/timer`

Create a new timer

**GET**

`/api/timer`

Retrieve the current timer

**PUT**

`/api/timer`

Update the existing timer

**DELETE**

`/api/timer`

Delete/reset the timer

----------

## 🧾 Example API Usage

```bash
# Create a timer
curl -X POST http://localhost:8080/api/timer \
  -H "Content-Type: application/json" \
  -d '{"name":"Launch Day","targetDate":"2025-12-31T23:59:59Z"}'

```

----------

## 🧰 Troubleshooting

Issue

Solution

**MongoDB connection failed**

Check IP whitelist and credentials in MongoDB Atlas.

**CORS error**

Ensure `@CrossOrigin(origins = "*")` is present in `TimerController`.

**Timer not updating in UI**

Refresh frontend and check console/network for errors.

----------

## 📱 UI Preview (Conceptual)

```
--------------------------------------------------
| ⏰ Countdown Timer                              |
| Counter Name: [ Launch Event       ]            |
| Target Date:  [ 2025-12-31  23:59 ]             |
| [ Start / Update ] [ Reset ]                    |
--------------------------------------------------
| Countdown: 25 Days 4 Hours 12 Minutes 8 Sec     |
--------------------------------------------------

```

----------

## 🏗️ Build for Production

### Backend

```bash
mvn clean package
java -jar target/countdown-backend-1.0.0.jar

```

### Frontend

```bash
npm run build

```

Serve the contents of `/dist` using **NGINX**, **Apache**, or **Spring static resources**.

----------

## 🧤 License

This project is licensed under the **MIT License**.

----------

## 👨‍💻 Author

**Aditya Shukla**  
_Full Stack Developer — Focused on efficiency, clean design, and practical architecture._
