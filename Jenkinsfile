def runCmd(String args) {
    if (isUnix()) {
        sh "chmod +x mvnw"
        sh "./mvnw ${args}"
    } else {
        bat "mvnw.cmd ${args}"
    }
}

pipeline {
    agent any

    environment {
        // Nombre descriptivo para el proyecto
        PROJECT_NAME = 'Pelardo'
    }

    stages {
        stage('📥 Checkout') {
            steps {
                echo "Descargando el código del repositorio..."
                git branch: 'main', url: 'https://github.com/Deyber99/Pelardo.git'
            }
        }

        stage('🔧 Compilar') {
            steps {
                echo "Compilando el proyecto con Maven..."
                runCmd 'clean compile'
            }
        }

        stage('🧪 Ejecutar Tests') {
            steps {
                echo "Ejecutando tests unitarios..."
                runCmd 'test'
            }
            post {
                always {
                    // Registra los resultados de las pruebas JUnit en Jenkins
                    junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
                }
            }
        }

        stage('🔍 Análisis SonarQube') {
            steps {
                echo "Iniciando el análisis de calidad de código con SonarQube..."
                withSonarQubeEnv('SonarQubeServer') {
                    runCmd 'sonar:sonar -Dsonar.projectKey=Pelardo'
                }
            }
        }

        stage('📦 Empaquetar') {
            steps {
                echo "Empaquetando la aplicación (creando JAR)..."
                runCmd 'package -DskipTests'
            }
            post {
                success {
                    // Guarda el archivo .jar generado para que puedas descargarlo desde la interfaz de Jenkins
                    archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
                }
            }
        }
    }

    post {
        success {
            echo "🎉 ¡Éxito! El pipeline se ejecutó correctamente y el JAR ha sido empaquetado."
        }
        failure {
            echo "❌ ¡Fallo! Algo salió mal durante la compilación, tests o empaquetado."
        }
    }
}
