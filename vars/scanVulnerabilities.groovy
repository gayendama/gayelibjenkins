#!/usr/bin/env groovy
def call () {
    // Scanner l'image Docker avec Trivy
    sh "trivy image --no-progress --exit-code 1 --severity HIGH ${imageName}:${imageTag}"
    sleep 10
}
