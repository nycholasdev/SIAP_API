#---------------------------------------------------------------------------#
#PRODUCTION MODE#

# FROM eclipse-temurin:17 AS build

# RUN apt-get update && \
#     apt-get install -y maven

# WORKDIR /app

# COPY pom.xml .
# RUN mvn dependency:go-offline

# COPY src ./src

# RUN mvn clean package -DskipTests

# FROM eclipse-temurin:17-jre

# WORKDIR /app

# COPY --from=build /app/target/*.jar app.jar

# EXPOSE 8080

# ENTRYPOINT ["java", "-jar", "app.jar"]


#---------------------------------------------------------------------------#

#DEVELOP MODE#
FROM eclipse-temurin:17

RUN apt-get update && \
    apt-get install -y maven

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]
