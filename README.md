# ⏰ Countdown Timer Web App (Dockerized + Monitored)

A full-stack **Countdown Timer** web application that allows users to:

- Set a **counter name** and **target date/time**
- Save timers to a **Spring Boot + MongoDB Atlas** backend
- Display a **live countdown** (days, hours, minutes, seconds)
- **Update** or **reset** the timer anytime
- Handle all API/database errors gracefully
- Expose **health and metrics endpoints** via Spring Boot Actuator
- Run easily via **Docker Compose**
- Fully **responsive** frontend built with React + Vite

---

## 🧱 Project Structure

```
countdown-timer/
├── backend/     → Spring Boot (Java 17)
│   ├── Dockerfile
│   ├── src/
│   ├── pom.xml
│   └── ...
├── frontend/    → React + Vite + Axios
│   ├── Dockerfile
│   ├── src/
│   ├── package.json
│   └── ...
└── docker-compose.yml
```

---

## 🚀 Tech Stack

| Layer            | Technology                                            |
| :--------------- | :---------------------------------------------------- |
| Frontend         | React, Vite, Axios, HTML5, CSS3                       |
| Backend          | Spring Boot (Web, Validation, Data MongoDB, Actuator) |
| Database         | MongoDB Atlas                                         |
| Monitoring       | Spring Boot Actuator, Logs                            |
| Containerization | Docker, Docker Compose                                |
| Language         | Java 17, JavaScript (ES6)                             |

---

## ⚙️ 1. Backend Setup (Spring Boot)

### 🧩 Prerequisites

- Java **17+**
- Maven **3.8+**
- MongoDB Atlas → [https://www.mongodb.com/cloud/atlas](https://www.mongodb.com/cloud/atlas)

---

### 🪣 Step 1: MongoDB Atlas Setup

1. Create a **Free Cluster** in MongoDB Atlas
2. Add a **Database User** (username/password)
3. Go to **Network Access → Allow Access from Anywhere (`0.0.0.0/0`)**
4. Copy your connection string (Driver → Java):

   ```
   mongodb+srv://<username>:<password>@<cluster>.mongodb.net/?retryWrites=true&w=majority
   ```

---

### ⚙️ Step 2: application.properties

```properties
spring.application.name=timer
server.port=8080

# MongoDB Atlas Connection
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/?appName=timers
spring.data.mongodb.database=timerdb
spring.data.mongodb.auto-index-creation=true

# Logging
logging.level.root=INFO
logging.level.com.user.timer=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
logging.file.name=logs/app.log
logging.file.max-size=10MB
logging.file.total-size-cap=100MB
logging.file.max-history=10

# Actuator Monitoring
management.endpoints.web.exposure.include=health,info,metrics,loggers,env
management.endpoint.health.show-details=always
management.endpoint.loggers.enabled=true
```

---

### ⚙️ Step 3: Advanced Error Handling

Implemented via:

- `GlobalExceptionHandler` → Catches validation, DB, and generic errors
- `ErrorResponse` → Returns structured JSON error messages
- `RequestLoggingFilter` → Logs all API requests with timings
- `Slf4j` → For structured log output

✅ Example Error Response:

```json
{
  "timestamp": "2025-11-07T12:35:00Z",
  "status": 503,
  "error": "Service Unavailable",
  "message": "Database unavailable. Please try again later.",
  "path": "/api/timer",
  "traceId": "3acb74e9"
}
```

---

### ⚙️ Step 4: Monitoring via Actuator

| Endpoint            | Description                    |
| ------------------- | ------------------------------ |
| `/actuator/health`  | Application and MongoDB health |
| `/actuator/info`    | App metadata                   |
| `/actuator/metrics` | Performance and memory metrics |
| `/actuator/loggers` | Live logger configuration      |

Example:

```bash
curl http://localhost:8080/actuator/health
```

Response:

```json
{
  "status": "UP",
  "components": {
    "diskSpace": { "status": "UP" },
    "mongo": { "status": "UP" }
  }
}
```

---

## 💻 2. Frontend Setup (React + Vite)

### 🧩 Prerequisites

- Node.js **18+**
- npm or yarn

---

### ⚙️ Step 1: Install

```bash
cd frontend
npm install
```

---

### ⚙️ Step 2: Configure Backend URL

📄 `src/services/api.js`

```javascript
// Local
const API_BASE = "http://localhost:8080/api/timer";

// Inside Docker
// const API_BASE = "http://backend:8080/api/timer";
```

---

### ⚙️ Step 3: Global Error Handling (Axios)

📄 `src/services/api.js`

```javascript
import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api/timer",
  timeout: 10000,
  headers: { "Content-Type": "application/json" },
});

api.interceptors.response.use(
  (res) => res,
  (error) => {
    if (!error.response) {
      alert("Cannot reach server. Try again later.");
    } else {
      const { status, data } = error.response;
      switch (status) {
        case 400:
          alert("Validation failed.");
          break;
        case 404:
          alert(data.message || "Timer not found.");
          break;
        case 503:
          alert("Database unavailable. Try again later.");
          break;
        default:
          alert(data.message || "Unexpected error occurred.");
      }
    }
    return Promise.reject(error);
  }
);

export const getTimer = () => api.get("");
export const createTimer = (data) => api.post("", data);
export const updateTimer = (data) => api.put("", data);
export const deleteTimer = () => api.delete("");

export default api;
```

---

## 🐳 3. Docker Setup

### 🧩 Backend Dockerfile

📄 `backend/Dockerfile`

```dockerfile
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

### 🧩 Frontend Dockerfile

📄 `frontend/Dockerfile`

```dockerfile
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
WORKDIR /usr/share/nginx/html
COPY --from=build /app/dist .
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```

---

### 🧩 Docker Compose File

📄 `docker-compose.yml`

```yaml
version: "3.9"

services:
  backend:
    build: ./backend
    container_name: countdown-backend
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/timerdb?retryWrites=true&w=majority
      - SPRING_DATA_MONGODB_DATABASE=timerdb
    networks:
      - countdown-net

  frontend:
    build: ./frontend
    container_name: countdown-frontend
    ports:
      - "5173:80"
    depends_on:
      - backend
    networks:
      - countdown-net

networks:
  countdown-net:
    driver: bridge
```

---

### ▶️ Run the Application

```bash
docker-compose up --build
```

✅ Then open:

- Frontend → [http://localhost:5173](http://localhost:5173)
- Backend → [http://localhost:8080/api/timer](http://localhost:8080/api/timer)
- Actuator → [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

---

### 🧹 Stop & Cleanup

```bash
docker-compose down
```

To remove images & volumes:

```bash
docker-compose down --rmi all --volumes
```

---

## 📊 Monitoring & Logging

- **Application Logs:**
  → Stored in `backend/logs/app.log`
- **API Requests:**
  → Logged via `RequestLoggingFilter`
- **Health Checks:**
  → `GET /actuator/health`
- **Metrics:**
  → `GET /actuator/metrics`
- **Error Tracking:**
  → Structured JSON returned by `GlobalExceptionHandler`

---

## 🧤 License

This project is licensed under the **MIT License**.

---

## 👨‍💻 Author

**Aditya Shukla**
_Full Stack Developer — Focused on efficiency, clean design, and practical architecture._
