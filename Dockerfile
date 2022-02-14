FROM gradle:7.1.1-jdk11 AS build
RUN mkdir /project/
COPY --chown=gradle:gradle . /project/
WORKDIR /project/
RUN gradle build --no-daemon

FROM adoptopenjdk/openjdk11:alpine-jre
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=build --chown=javauser:javauser /project/build/libs/*.jar /opt/app.jar
USER javauser
ENTRYPOINT ["java","-jar","/opt/app.jar"]
