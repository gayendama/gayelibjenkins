#!/usr/bin/env groovy
def call(String imageName, String imageTag) {
    sh "docker build -t ${imageName}:${imageTag} ."
}

