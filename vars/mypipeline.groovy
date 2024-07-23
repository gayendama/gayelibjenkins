#!/usr/bin/env groovy

def call(Map config) {
    def PROJET = config.PROJET ?: "Test House innovation"
    def ID_DOCKER = config.ID_DOCKER ?: "ndamagaye268"
    def imageName = config.IMAGE_NAME ?: "house-innovation"
    def imageTag = config.IMAGE_TAG ?: "latest"
    def PortApp = config.PortApp ?: 80
    def PortContainer = config.PortContainer ?: 8200
    pipeline {
        agent any
        environment {
            DOCKER_PASSWORD = credentials('DOCKER_PASSWORD')
        }
        stages {
            stage('Build Docker image') {
                steps {
                    script {
                        buildDockerImage(env.imageName, env.imageTag)
                    }
                }
            }

            stage('Test Acceptance') {
                steps {
                    script {
                        testAcceptance("house-innovation", "latest", 8200, 80)
                    }
                }
            }
            stage('Run JMeter Tests') {
            steps {
                script {
                    sh """
                    mkdir -p /home/ndama/jmeter/apache-jmeter-5.6.3/jmeter-results
                    /home/ndama/jmeter/apache-jmeter-5.6.3/bin/jmeter -n -t testlocal.jmx -l jmeter-results/results.jtl -e -o jmeter-results/report
                    """
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
