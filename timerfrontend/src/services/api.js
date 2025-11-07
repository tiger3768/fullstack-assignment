import axios from "axios";

const API_BASE = "http://localhost:8080/api/timer";

export const getTimer = async () => axios.get(API_BASE);
export const createTimer = async (data) => axios.post(API_BASE, data);
export const updateTimer = async (data) => axios.put(API_BASE, data);
export const deleteTimer = async () => axios.delete(API_BASE);
