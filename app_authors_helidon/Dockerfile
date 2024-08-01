# docker build -t app-authors .
FROM eclipse-temurin:21-jre-jammy

RUN mkdir /app
WORKDIR /app

COPY build/install/app_authors_helidon/lib .

EXPOSE 8080

CMD ["java","-jar","app_authors_helidon.jar"]
