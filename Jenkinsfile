pipeline {
    agent{
        docker {
            image 'maven:3-eclipse-temurin-11'
        }
    }
    
    stages{
        /*stage('Test'){
            steps{
                sh 'mvn test'

                archiveArtifacts artifacts: 'target/JMD-*.jar'
            }
        }*/

        stage('Build'){
            steps{
                sh 'mvn package -Dmaven.test.skip=true'
                
                archiveArtifacts artifacts: 'target/DocuServe-*.jar'
            }
        }
    }
}
