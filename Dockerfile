FROM openjdk:21-slim
LABEL maintainer="frankmwangi8118@gmail.com"
LABEL version="1.0.0"
LABEL description="sales sync app"
LABEL collaborators="@mosidev,@mwas"
WORKDIR /app
COPY target/ioio-0.0.1-SNAPSHOT.jar apps.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","apps.jar"]
