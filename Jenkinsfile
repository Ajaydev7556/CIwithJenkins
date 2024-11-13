pipeline {
  agent any
  stages {
    stage('Maven Build') {
      steps {
        sh 'mvn clean install'
      }
    }

    stage('TEST Runner') {
      steps {
        sh 'mvn test'
      }
    }

  }
}