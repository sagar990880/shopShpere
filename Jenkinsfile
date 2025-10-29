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

        stage('Build Microservices') {
            steps {
                echo "🔹 Building all microservices..."
                sh '''
                    cd discovery-server && mvn clean package -DskipTests
                    cd ../apigateway && mvn clean package -DskipTests
                    cd ../product-service && mvn clean package -DskipTests
                    cd ../order_service && mvn clean package -DskipTests
                    cd ../payment_service && mvn clean package -DskipTests
                '''
            }
        }

        stage('Docker Build') {
            steps {
                echo "🔹 Building Docker images..."
                // docker build commands yahan likh (har service ke liye)
            }
        }

        stage('Deploy') {
            steps {
                echo "🚀 Deploying services..."
                // docker-compose up -d etc.
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