### the starter springboot project for docker

1. clone project & package

```
# clone
$ git clone https://github.com/WinMoney/docker-java-starter.git & cd docker-java-starter

# package with maven
$ mvn clean package -DskipTests

```

2. build image

```
# build image
# docker-java-starter : image name
# 1.0-snapshot : image tag

$ docker build -t docker-java-starter:1.0-snapshot .

# check image
$ docker images

REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
docker-java-starter   1.0-snapshot        debdec78d957        9 seconds ago       123MB
```

3. create & run docker container
```
docker-starter : container name
$ docker run -d -p 13364:13364 --name docker-starter docker-java-starter:1.0-snapshot

# check container status
$ docker ps

CONTAINER ID        IMAGE                              COMMAND                  CREATED             STATUS              PORTS                      NAMES
ac222a67abd1        docker-java-starter:1.0-snapshot   "/bin/sh -c 'exec ja…"   7 seconds ago       Up 6 seconds        0.0.0.0:13364->13364/tcp   docker-starter

# tail logs
$ docker logs -f docker-starter
```

##### save & load image

- save image
```
$ docker image save -o docker-java-starter_1.0-snapshot.tar docker-java-starter:1.0-snapshot
```

- load image
```
$ docker load < docker-java-starter_1.0-snapshot.tar
or
$ docker load -i docker-java-starter_1.0-snapshot.tar
```