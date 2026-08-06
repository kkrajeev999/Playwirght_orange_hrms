FROM mcr.microsoft.com/playwright/java:v1.55.0-noble

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

CMD ["mvn","clean","test"]