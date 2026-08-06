FROM mcr.microsoft.com/playwright/java:v1.59.0-noble

WORKDIR /app

# Copy pom.xml first for better Docker layer caching
COPY pom.xml .

# Download Maven dependencies
RUN mvn dependency:go-offline

# Copy the rest of the project
COPY . .

# Compile the project
RUN mvn clean test-compile

# Run tests
CMD ["mvn", "clean", "test"]