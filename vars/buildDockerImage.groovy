#!/usr/bin/env groovy
def buildDockerImage(String repository, String version) {
    sh "docker build -t ${repository}:${IMAGE_VERSION} ."
}

