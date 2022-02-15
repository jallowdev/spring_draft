# Configuration in spring boot 
## application.properties
- 1 Injection des propriétés avec @Value({properties})
- 2 Ajout de fichiers de propriétés avec @PropertySource
- 3 Les variables d’environnement
- 4 La classe Environment
- 5 Beans de propriétés de config
- 6 Importation de fichiers de configuration supplémentaires
````
spring.config.import=classpath:additional-application.properties,
  classpath:additional-application[.yml],
  optional:file:./external.properties,
  classpath:additional-application-properties/
````
- 6 Fichier de propriétés spécifiques au test
    - Il convient également de mentionner que les fichiers YAML ne prennent pas en charge l' annotation @PropertySource , donc si nous devons utiliser cette annotation, cela nous obligerait à utiliser un fichier de propriétés.
- 6 Command line
````
java -jar app.jar --property="value" //regle generale
java -jar target/spring_draft-0.0.1-SNAPSHOT.jar --server.port=9090
java -jar target/spring_draft-0.0.1-SNAPSHOT.jar --spring.profiles.active={envirement}
java -jar app.jar --spring.config.location=classpath:/another-location.properties
java -jar app.jar --spring.config.location=config/*/
````

### Resources Link
- 1 https://gayerie.dev/docs/spring/spring/application_context_conf.html
- 2 https://www.baeldung.com/properties-with-spring