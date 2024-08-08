pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage("init") {
            steps {
                script {
                   gv = load "script.groovy" 
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    gv.buildJar()
                    sh "mvn package"
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    gv.buildImage()
                    withCredentials([usernamePassword(credentialsId:'dockerhub-cred',passwordVariable:'PASS',usernameVariable:'USER')]) {
                        sh 'docker build -t vidhi2002/my-repo:jma-2.0 . '
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push vidhi2002/my-repo:jma-2.0'
                    }
                }
            }
        }
        stage("test") {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage("deploy") {
            input {
                message "Select the environment to deploy to"
                ok "Done"
                parameters {
                    choice(name: 'ENV', choices: ['DEV', 'QA', 'PROD'], description: '')
                }
            }
            steps {
                script {
                    gv.deployApp()
                    echo "deploying to ${ENV}"
                }
            }
        }
    }   
}