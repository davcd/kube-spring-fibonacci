# Kube Spring Fibonacci

Containerized microservice that exposes an endpoint for calculating the n-th sequence of fibonacci.

> This is a project for testing purposes.

## Prerequisites 

- Java 11
- Minikube
- Docker

## Microservice

### Features
- Microservice developed using Spring Boot
- Fibonacci sequence calculated using fast doubling method

### Run

```bash
> ./gradlew bootRun
```

### Test

```bash
> curl http://localhost:8000/fib?n=1
1

> curl http://localhost:8000/fib?n=10
55

> curl http://localhost:8000/fib?n=72
498454011879264
```


## Containerization

### Features
- Multi-stage Dockerfile
- Docker compose file included
- Kubernetes with Horizontal Pod Autoscaler

### Docker image build

```bash
> docker build -t <docker-image-name> .
```

### Docker container run

```bash
> docker compose up
```

### Kubernetes deploy

> Please enable `metrics-server` minikube addon in order to allow HPA to work properly.

```bash
> minikube addons enable metrics-server
> minikube start
```
```bash
> kubectl apply -f fib.yaml
> minikube tunnel
```

### Kubernetes manual scale

```bash
> kubectl scale --replicas=<expected_replica_num> deployment kube-spring-fibonacci-deployment
```
