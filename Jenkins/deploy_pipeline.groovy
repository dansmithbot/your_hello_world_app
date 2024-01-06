pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                script {
                    git branch: 'main', credentialsId: 'bc55c06a-9c3c-4734-8c20-451153d96c5c', url: 'https://github.com/dansmithbot/your_hello_world_app.git'
                }
            }
        }
        
        stage('Prepare') {
            steps {
                sh 'mkdir -p target'
            }
        }
        
        stage('Build') {
            steps {
                dir('src') {
                    sh 'javac -d ../target HelloWorld.java'
                }

                dir('target') {
                    def timestamp = sh(script: 'date +%Y%m%d%H%M%S', returnStdout: true).trim()
                    sh "jar cvf hello-world-${timestamp}.jar -C ../target HelloWorld.class"
                }
            }
        }
        
        stage('Deploy to Raspberry Pi') {
            steps {
                sh 'ls -l target/'
                sh 'mv target/hello-world-*.jar target/artifacts/'
            }
        }
    }
}
