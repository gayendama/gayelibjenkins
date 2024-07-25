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
            RESULTS_DIR = '/var/lib/jenkins/workspace/pipeline/jmeter-results'
            REPORT_DIR = "${RESULTS_DIR}/report"
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
                    sh 'sudo /home/ndama/jmeter/apache-jmeter-5.6.3/bin/jmeter -n -t testPlan.jmx -l results.jtl'
                    sh 'cat results.jtl'
                    perfReport 'results.jtl'
                }
            }
        }            
            
         
         stage('Cleanup') {
            steps {
                // Arrêter et supprimer le conteneur Docker
                sh "docker stop house-innovation"
                sh "docker rm house-innovation"
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
