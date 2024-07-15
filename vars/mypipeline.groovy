def call(Map params = [:]) {
    pipeline {
        agent any

        environment {
            IMAGE_VERSION = params.imageVersion ?: "${env.BUILD_NUMBER}"
            DOCKER_REPOSITORY = params.dockerRepository ?: 'default/repository'
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
