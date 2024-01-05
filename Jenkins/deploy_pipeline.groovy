pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Pull the code from the main branch of your Git repository using SSH credentials
                script {
                    git branch: 'main', credentialsId: 'bc55c06a-9c3c-4734-8c20-451153d96c5c', url: 'https://github.com/dansmithbot/your_hello_world_app.git'
                }
            }
        }
        
        stage('Build') {
            steps {
                // Compile Java source files - Adjust based on your Java project structure
                sh 'javac -d target src/*.java'
            }
        }
        
        stage('Deploy to Raspberry Pi') {
            steps {
                // Move the built artifact (hello-world-1.0.0.jar) to /home/pi/hello/ on the Pi
                sh 'mv target/hello-world-1.0.0.jar /home/pi/hello/' // Using hello-world-1.0.0.jar as the artifact name
            }
        }
    }
}
