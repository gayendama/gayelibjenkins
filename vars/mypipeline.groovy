def call(Map config) {
    pipeline {
        agent any

        environment {
        DOCKER_REPOSITORY = 'ndamagaye286'  // Remplacez par le nom de votre repository
        IMAGE_VERSION = "${env.BUILD_NUMBER}"    // Utilise le numéro de build Jenkins comme version de l'image
    }
    stages {
        stage('Build Docker image') {
            steps {
                script {
                    buildDockerImage(env.DOCKER_REPOSITORY, env.IMAGE_VERSION)
                }
            }
        }
    }
}
}
