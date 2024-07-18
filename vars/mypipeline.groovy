#!/usr/bin/env groovy

def call(Map config) {
    pipeline {
        agent any

        environment {
            DOCKER_REPOSITORY = 'ndamagaye286'  // Remplacez par le nom de votre repository
            IMAGE_VERSION = "${env.BUILD_NUMBER}"    // Utilise le numéro de build Jenkins comme version de l'image
            IMAGE_NAME = config.imageName ?: "house-innovation"
            PortContainer = config.portContainer ?: "8200"
            PortApp = config.portApp ?: "80"
            IMAGE_TAG = config.imageTag ?: "latest"
        }

        stages {
            stage('Build Docker image') {
                steps {
                    script {
                        buildDockerImage(env.DOCKER_REPOSITORY, env.IMAGE_VERSION)
                    }
                }
            }

            stage('Test Acceptance') {
                steps {
                    script {
                        testAcceptance()
                    }
                }
            }

            stage('Scan Vulnérabilité Image') {
                steps {
                    script {
                        scanVulnerabilities()
                    }
                }
            }
        }
    }
}
