# --- Build stage (Maven) ---
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# copy pom trước để cache dependencies
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# copy src và build WAR
COPY src ./src
RUN mvn -q -DskipTests clean package

# --- Run stage (Tomcat 10/JDK17) ---
FROM tomcat:10.1-jdk17-temurin
# bỏ webapps mẫu cho nhẹ
RUN rm -rf /usr/local/tomcat/webapps/*

# nếu trong pom.xml bạn đã đặt <finalName>ch12email</finalName>
COPY --from=build /app/target/ch12email.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh","run"]
