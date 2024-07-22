#!/usr/bin/env groovy
def call(String IMAGE_NAME, String IMAGE_TAG) {
    sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
}

