# Étape 1: Build de l'application avec Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compile le projet et ignore les tests pour accélérer le build de déploiement
RUN mvn clean package -DskipTests

# Étape 2: Lancement de l'application (JRE allégé)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copie le .jar généré depuis l'étape précédente
COPY --from=build /app/target/*.jar app.jar
# Indique le port par défaut (bien qu'écrasé par Render)
EXPOSE 8080
# Commande de démarrage
ENTRYPOINT ["java", "-jar", "app.jar"]
