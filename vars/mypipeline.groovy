pipeline {
    agent any

    environment {
       IMAGE_VERSION = "${env.BUILD_NUMBER}"  // Utilise le numéro de build Jenkins comme version de l'image
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
