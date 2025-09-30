#!/bin/bash

# Script to install Java 17, Maven & docker on Ubuntu 22 LTS

# Exit immediately if a command exits with a non-zero status
set -e

# Print commands before executing them
set -x

######### Java 17 Installation ######### 
echo "Starting Java 17 installation..."

# Update package lists
sudo apt update

# Install OpenJDK 17
sudo apt install -y openjdk-17-jdk

# Verify Java installation
echo "Verifying Java installation..."
java -version

echo "Java 17 installation completed successfully!"

######### Maven Installation ######### 
echo "Starting Maven installation..."

# Update package lists
sudo apt update

# Install Maven
sudo apt install -y maven

# Verify Maven installation
echo "Verifying Maven installation..."
mvn -version

# Verify Maven installation
echo "Maven installation completed successfully!"

######### Docker Installation ######### 
echo "Starting Docker installation..."

# Update package lists
sudo apt update

# Install Docker
sudo apt install -y docker.io

# Verify Docker installation
echo "Verifying Docker installation..."
docker --version

echo "Docker installation completed successfully!"

# Add user to docker group
groupadd docker
sudo usermod -aG docker $USER

sudo apt update



