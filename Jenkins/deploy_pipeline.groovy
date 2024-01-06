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
        
        stage('Prepare') {
            steps {
                // Create target directory
                sh 'mkdir -p target'
            }
        }
        
        stage('Build') {
            steps {
                // Compile Java source files - Adjust based on your Java project structure
                dir('src') {
                    sh 'javac -d ../target HelloWorld.java'
                    sh 'jar -cvf ../target/hello-world-1.0.0.jar -C ../target HelloWorld.class'
                }
            }
        }
        
        stage('Deploy to Raspberry Pi') {
            steps {
                // List contents of the target directory for debugging
                sh 'ls -l target/'
                
                // Move the built artifact to the 'artifacts' subfolder
                sh 'mv target/hello-world-1.0.0.jar target/artifacts/'
                
                // Move the artifacts to /home/pi/hello/ on the Pi
                sh 'mv target/artifacts/* /home/pi/hello/'
            }
        }
    }
}
