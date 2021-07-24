FROM openjdk:11
# copy WAR into image
COPY build/libs/energy-0.0.1-SNAPSHOT.jar /energy.jar
# run application with this command line
CMD ["java", "-jar", "-Dspring.profiles.active=default", "/energy.jar"]
