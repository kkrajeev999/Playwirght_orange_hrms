FROM mcr.microsoft.com/playwright/java:v1.59.0-noble

WORKDIR /app

COPY . .

RUN mvn clean test-compile

CMD ["mvn","clean","test"]