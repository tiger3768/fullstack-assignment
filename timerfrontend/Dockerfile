# ---- Stage 1: Build React App ----
FROM node:20-alpine AS build
WORKDIR /app

# Copy and install dependencies
COPY package*.json ./
RUN npm install

# Copy project files and build
COPY . .
RUN npm run build

# ---- Stage 2: Serve via Nginx ----
FROM nginx:alpine
WORKDIR /usr/share/nginx/html

# Copy build output from previous stage
COPY --from=build /app/dist .

# Expose Nginx port
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
