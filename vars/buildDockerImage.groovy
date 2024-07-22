#!/usr/bin/env groovy
def call(String IMAGE_NAME, String version) {
    sh "docker build -t ${IMAGE_NAME}:${version} ."
}

