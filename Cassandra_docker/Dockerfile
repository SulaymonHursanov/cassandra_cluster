# sudo docker run -it --name eapp --hostname test ubuntu bash
FROM ubuntu:18.04
LABEL maintainer "Sulaymon"

ARG node1
ARG node2
ARG node3

# To be sure args are accepted
RUN echo "node 1: $node1"
RUN echo "node 2: $node2"
RUN echo "node 3: $node3"

# Args converting to ENV variables
ENV node1=$node1
ENV node2=$node2
ENV node3=$node3

# Setting timezone
ENV TZ=Europe/Moscow

# updating source list 
RUN echo "deb http://in.archive.ubuntu.com/ubuntu/ bionic main restricted universe multiverse" > /etc/apt/sources.list
RUN apt-get -y update


# Installing additional packages to install python and java
RUN apt-get install software-properties-common -y

ENV JAVA_VER 8
ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk-amd64

RUN apt-get update

# Install java 
RUN apt-get install -y openjdk-8-jre-headless

# Install python 
RUN apt-get install -y python-minimal

# Python version
RUN python -V

# Install cassandra
COPY cassandra-install.sh .
RUN chmod +x cassandra-install.sh
RUN ./cassandra-install.sh

# Config cassandra 
COPY cassandra-config.sh .
RUN chmod +x cassandra-config.sh
ENTRYPOINT ./cassandra-config.sh $node1 $node2 $node3

# Opening ports
EXPOSE 7000 7001 7199 9042 9160
