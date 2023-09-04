#!/bin/bash
IMAGE_NAME="my-jenkins"

SCRIPT_DIR=$(dirname "$0")
DOCKERFILE_DIR="$SCRIPT_DIR/jenkins"

BUILD_CONTEXT="$SCRIPT_DIR/.."

docker build -t $IMAGE_NAME -f $DOCKERFILE_DIR/Dockerfile-jenkins $SCRIPT_DIR

# Run the Docker container with the Maven .m2 directory mapped as a volume
docker run -p 8080:8080 -p 50000:50000 \
  -v $SCRIPT_DIR:/var/jenkins_home/workspace \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v ~/.m2:/var/jenkins_home/.m2 \
  $IMAGE_NAME