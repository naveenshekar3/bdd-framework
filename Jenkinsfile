pipeline{
    agent any

    tools{
        maven 'Maven-3.6.3'
        jdk  'JDK-17'
    }

    environment{
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=true"
        ALLURE_RESULTS = "target/allure-results"
    }

    stages{
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Clean & Compile') {
            steps{
                sh 'mvn clean compile'
            }
        }

        stage('Run Automation Tests'){
            steps{
                sh 'mvn test'
            }
        }

        stage('Generate Allure Report'){
            steps{
                allure(
                    includeProperties: false,
                    jdk: '',
                    results: [[path:"${ALLURE_RESULTS}"]]
                )
            }
        }
    }
    post{
        always{
            echo 'Pipeline execution completed'
            archiveArtificats artifacts: '**/target/screenshots/*.png', allowEmptyArchive: true
        }

        success{
            echo 'Build SUCCESS'
        }

        failure{
            echo 'Build FAILED'
        }
    }
}