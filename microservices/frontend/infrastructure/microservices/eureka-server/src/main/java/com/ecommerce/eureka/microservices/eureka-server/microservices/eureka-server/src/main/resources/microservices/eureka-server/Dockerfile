FROM openjdk:17-jdk-slim

VOLUME /tmp

ARG JAR_FILE=target/eureka-server-1.0.0.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8761

HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8761/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "/app.jar"]
