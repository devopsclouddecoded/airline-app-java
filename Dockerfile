FROM openjdk:17-jdk-slim

# Install curl and other utilities
RUN apt-get update && \
    apt-get install -y curl iputils-ping dnsutils netcat && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy the jar file
COPY target/*.jar app.jar

# Expose the port
EXPOSE 9095

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]