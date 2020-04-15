# Jaeger Sample using the Springboot

Quick and dirty Spring Boot project with some basic Jaeger tracing enabled.

Use gradle build copyLocal to run from local subdirectory.

Use gradele build copyDeps to run in container. Build image in docker subdirectory.

###### Docker Jaeger installation 
docker run -d --name jaeger -p 16686:16686 -p 6831:6831/udp jaegertracing/all-in-one:1.9

Above docker image uses non production image (all-in-one) data gets stored in in-memory rather than casandra or ES
Access UI @ http://localhost:16686/search

all-in-one
----------
https://www.jaegertracing.io/docs/1.17/getting-started/

Jaeger Screenshots
------------------
![](images/with-delay-spans.JPG)
![](images/without-delay-spans.JPG)

Scaling Jaegar
1. https://www.youtube.com/watch?v=tFZAHWl8y_I

