#!/usr/bin/env groovy
def testAcceptance() {
    // Démarrer le conteneur Docker
    sh "docker run --name ${IMAGE_NAME} -d -p ${PortContainer}:${PortApp} ${ID_DOCKER}/${IMAGE_NAME}:${IMAGE_TAG}"

    // Attendre que le conteneur soit prêt (optionnel, selon votre application)
    sleep 10

    // Test d'acceptation avec cURL
    sh "curl localhost:${PortContainer} | grep -q 'Author: Roody95'"

    // Arrêter et supprimer le conteneur Docker
    sh "docker stop ${IMAGE_NAME}"
    sh "docker rm ${IMAGE_NAME}"
}
