# Estate

# Présentation générale

Ce dépôt contient le code du back-end de l’application Rental, développé en Java avec Spring Boot. Il fournit des endpoints REST pour la gestion des utilisateurs, des locations (rentals) et des messages.

# Installation et lancement Front-end

Clonez le dépot GitHub :

git clone https://github.com/RyShad0wz/Developpez-le-back-end-en-utilisant-Java-et-Spring

Installation des dépendences :

npm install

npm run start ou ng serve

# Installation et lancement Back-end


Clonez le dépôt GitHub sur votre machine.

git clone https://github.com/RyShad0wz/Developpez-le-back-end-en-utilisant-Java-et-Spring
cd backend

Dans le fichier « src/main/resources/application.properties », vérifiez la configuration suivante : 

• Base de données MySQL :

spring.datasource.url=jdbc:mysql://localhost:3306/rental_db?createDatabaseIfNotExist=true&serverTimezone=UTC

spring.datasource.username=${DB_USERNAME}

spring.datasource.password=${DB_PASSWORD} 

• Hibernate (création/mise à jour automatique des tables) :

spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.show_sql=true

spring.jpa.open-in-view=false

• Clé de signature JWT :

jwt.signing.key=${JWT_SIGNING_KEY}

Ajoutez les variables d'environnement dans les parametres Windows

Lancez l’application en exécutant la classe principale (RentalApplication) depuis votre IDE ou via Maven.

L’application démarre sur le port 8080.

# Installation de la base de données

Si nécessaire, créez la base de données « rental_db » avec MySQL. 

(La propriété createDatabaseIfNotExist=true permet de la créer automatiquement si elle n’existe pas.) 

Les entités définies dans le code (USERS, RENTALS, MESSAGES, etc.) seront créées/mises à jour automatiquement grâce à Hibernate.

# Documentation de l’API (Swagger)

La documentation interactive de l’API est fournie par Springdoc OpenAPI. • Swagger UI : http://localhost:8080/swagger-ui/index.html

• Documentation OpenAPI JSON : http://localhost:8080/v3/api-docs

Pour tester les routes sécurisées, faire une réquête register ou login, récupérer le token renvoyé et l'ajouter dans le Bearer

# Endpoints principaux

• Authentification et Utilisateurs

– POST /auth/register : inscription d’un nouvel utilisateur

– POST /auth/login : authentification et obtention d’un token JWT

– GET /auth/me : récupération des informations de l’utilisateur connecté

– GET /api/users/{id} : récupération d’un utilisateur par ID

– POST /api/users/register : alternative pour l’inscription

• Locations (Rentals)

– GET /api/rentals : liste de toutes les locations

– GET /api/rentals/{id} : récupération d’une location par ID

– POST /api/rentals : création d’une location (authentification requise)

– PUT /api/rentals/{id} : mise à jour d’une location (authentification requise)

• Messages

– GET /api/messages/rental/{rentalId} : liste des messages d’une location

– POST /api/messages : envoi d’un message (authentification requise)

# Environnements

Mockoon env

Download Mockoon here: https://mockoon.com/download/

After installing you could load the environement

    ressources/mockoon/rental-oc.json

directly inside Mockoon

    File > Open environmement

For launching the Mockoon server click on play bouton

Mockoon documentation: https://mockoon.com/docs/latest/about/
Postman collection

For Postman import the collection

    ressources/postman/rental.postman_collection.json

by following the documentation:

https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman


# Remarques

– Les endpoints REST sont documentés via Swagger (voir point 4).

– La sécurité est assurée par JWT et une configuration Spring Security adaptée.

– Les données de la base sont gérées automatiquement via Hibernate.

