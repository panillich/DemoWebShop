pipeline {
    agent any
    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Select Browser')
        choice(name: 'SUITE', choices: ['src/test/resources/regression.xml', 'src/test/resources/quick.xml'], description: 'Select Test Suite')
    }

    tools {
        maven 'Maven 3.8'
        jdk 'Java 21'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                sh "mvn clean test -DsuiteXmlFile=${params.SUITE} -Dbrowser=${params.BROWSER}"
            }
            post {
                always {
                    junit '**/target/surefire-reports/junitreports/*.xml'
                }
            }
        }
    }
}