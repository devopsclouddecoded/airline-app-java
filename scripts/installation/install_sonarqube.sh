#!/bin/bash

# ======================================================
# SonarQube Installation Script for Ubuntu
# ======================================================
# Description: This script installs SonarQube 9.9.5 on Ubuntu
# ======================================================

# Exit immediately if a command exits with a non-zero status
set -e

# Install unzip utility needed to extract SonarQube zip file
sudo apt install unzip -y

# Install net-tools for network diagnostics (ifconfig, netstat, etc.)
sudo apt install net-tools

# Download SonarQube 9.9.5 from the official source
# This is the Community Edition which is free and open-source
sudo wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-9.9.5.90363.zip

# Rename the downloaded file for easier handling
sudo mv sonarqube-9.9.5.90363.zip sonarqube.zip

# Move the zip file to the /opt directory (common location for optional software)
sudo mv sonarqube.zip /opt/

# Change directory to /opt
cd /opt/

# Extract the SonarQube zip file
sudo unzip sonarqube.zip

# Create a dedicated user 'sonaradmin' to run SonarQube
# This is a security best practice - don't run services as root
sudo adduser sonaradmin

# Change ownership of the SonarQube directory to the sonaradmin user
# This ensures the sonaradmin user has the necessary permissions
sudo chown -R sonaradmin:sonaradmin sonarqube

# Navigate to the SonarQube bin directory containing the startup script
cd sonarqube/bin/linux-x86-64/

# Switch to the sonaradmin user to start SonarQube
# This ensures SonarQube runs with the correct permissions
sudo su sonaradmin

# Start the SonarQube server
# SonarQube will run on port 9000 by default
./sonar.sh start

# Check the status of the SonarQube server
# This will show if SonarQube is running correctly
./sonar.sh status

# Note: After installation, SonarQube will be accessible at:
# http://your-server-ip:9000
# Default credentials: admin/admin