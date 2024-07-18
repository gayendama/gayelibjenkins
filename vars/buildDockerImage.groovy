#!/usr/bin/env groovy
def call(String repository, String version) {
    sh "docker build -t ${repository}:${version} ."
}

