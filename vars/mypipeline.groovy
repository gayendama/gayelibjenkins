pipeline {
    agent any

    environment {
        DOCKER_REPOSITORY = 'ndamagaye286'
        IMAGE_VERSION = "${env.BUILD_NUMBER}"
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
