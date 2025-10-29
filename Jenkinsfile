pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        PATH = "${tool 'Maven3'}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "🔹 Pulling code from GitHub..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "🔹 Building all microservices..."
                sh 'mvn -v'   // ✅ check Maven available
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo "🔹 Building Docker images..."
                // docker build commands here
            }
        }

        stage('Deploy') {
            steps {
                echo "🚀 Deploying services..."
                // deployment steps
            }
        }
    }

    post {
        success {
            echo "✅ Deployment successful!"
        }
        failure {
            echo "❌ Deployment failed!"
        }
    }
}