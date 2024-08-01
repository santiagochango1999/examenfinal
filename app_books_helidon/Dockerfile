# docker build -t app-authors .
FROM eclipse-temurin:21-jre-jammy

RUN mkdir /app
WORKDIR /app

COPY build/install/app_books_helidon/lib .

EXPOSE 8081

CMD ["java","-jar","app_books_helidon.jar"]
