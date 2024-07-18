#!/usr/bin/env groovy

def call(Map config) {
    pipeline {
        agent any

        environment {
            repository = 'ndamagaye286'  // Remplacez par le nom de votre repository
            version = "${env.BUILD_NUMBER}"    // Utilise le numéro de build Jenkins comme version de l'image
            imageName = config.imageName ?: "house-innovation"
            portContainer = config.portContainer ?: "8200"
            portApp = config.portApp ?: "80"
            imageTag = config.imageTag ?: "latest"
        }

        stages {
            stage('Build Docker image') {
                steps {
                    script {
                        buildDockerImage(env.repository, env.version)
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
