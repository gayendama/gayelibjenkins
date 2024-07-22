#!/usr/bin/env groovy
def  call(String IMAGE_NAME, String IMAGE_TAG, int PortContainer, int PortApp  ) {
    // Démarrer le conteneur Docker
    sh "docker run --name ${IMAGE_NAME} -d -p ${PortContainer}:${PortApp} ${IMAGE_NAME}:${IMAGE_TAG}"
    sleep 10
   
}
