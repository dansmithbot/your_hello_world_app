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
                // sh 'mkdir -p target'
                sh 'rm -rf target'
            }
        }
        
        // stage('Build') {
        //     steps {
        //         // Compile Java source files - Adjust based on your Java project structure
        //         dir('src') {
        //             sh 'javac -d ../target HelloWorld.java'
        //         }

        //         // Generate timestamp for versioning
        //         dir('target') {
        //             timestamp = sh(script: 'date +%Y%m%d%H%M%S', returnStdout: true).trim()
        //             sh "jar cvf hello-world-${timestamp}.jar -C ../target HelloWorld.class"
        //         }
        //     }
        // }
        
        // stage('Deploy to Raspberry Pi') {
        //     steps {
        //         // List contents of the target directory for debugging
        //         sh 'ls -l target/'
                
        //         // Move the built artifact to the 'artifacts' subfolder
        //         sh 'mv target/hello-world-*.jar target/artifacts/'
        //     }
        // }
    }
}
