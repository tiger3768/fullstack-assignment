import axios from "axios";

// 🧠 Create Axios instance with base URL
const api = axios.create({
  baseURL: "http://backend:8080/api/timer", // backend URL
  timeout: 10000, // 10s timeout
  headers: {
    "Content-Type": "application/json",
  },
});

// 🛡️ Response Interceptor — handles all backend errors
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (!error.response) {
      // Server unreachable or network issue
      console.error("❌ Network error:", error);
      alert(
        "⚠️ Cannot connect to server. Please check your network or try again later."
      );
    } else {
      const { status, data } = error.response;

      console.group("⚠️ API Error");
      console.log("Status:", status);
      console.log("Response:", data);
      console.groupEnd();

      // Handle by HTTP status
      switch (status) {
        case 400:
          alert(
            `❌ Validation Error:\n${JSON.stringify(
              data.messages || data.message
            )}`
          );
          break;

        case 404:
          alert(`⚠️ ${data.message || "Timer not found"}`);
          break;

        case 503:
          alert("🚧 Service unavailable. Try again later.");
          break;

        case 500:
          alert(
            `💥 Server Error: ${data.message || "Unexpected error occurred."}`
          );
          break;

        default:
          alert(`⚠️ Unexpected error: ${data.message || "Please try again."}`);
      }
    }

    return Promise.reject(error);
  }
);

// ✅ Export API methods (reuse throughout app)
export const getTimer = () => api.get("");
export const createTimer = (data) => api.post("", data);
export const updateTimer = (data) => api.put("", data);
export const deleteTimer = () => api.delete("");

export default api;
