#!/usr/bin/env groovy
def scanVulnerabilities() {
    // Scanner l'image Docker avec Trivy
    sh "trivy image --no-progress --exit-code 1 --severity HIGH ${IMAGE_NAME}:${IMAGE_TAG}"
}
