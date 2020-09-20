pipeline {
    agent any

    tools {
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                sh "mvn clean install -DskipTests=true -Dmaven.javadoc.skip=true -B -V"
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }

        }
        stage('Generate Documentation') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('Code Analysis') {
            parallel {
                stage('Unit Tests') {
                    steps {
                        sh 'mvn clean test'
                    }
                    post {
                        success {
                            junit '**/target/surefire-reports/TEST-*.xml'
                        }
                    }
                }
                stage('Static Analysis') {
                    steps {
                        sh 'mvn --version'
                        //https://github.com/eclipse/che-che4z-lsp-for-cobol/pull/74/files
                        withCredentials([string(credentialsId: 'sonarcloud-token', variable: 'SONARCLOUD_TOKEN')]) {
                            sh 'mvn sonar:sonar -Dsonar.projectKey=Mitschi_restservice-example -Dsonar.organization=mitschi -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=${SONARCLOUD_TOKEN}'
                            //sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar -Dsonar.projectKey=Mitschi_restservice-example'
                        }
                    }
                }
                stage('Mutation Testing') {
                    steps {
                        sh 'mvn clean test org.pitest:pitest-maven:mutationCoverage'
                    }
                    post {
                        success {
                            pitmutation killRatioMustImprove: false, minimumKillRatio: 50.0, mutationStatsFile: '**/target/pit-reports/**/mutations.xml'
//                            archiveArtifacts 'target/*.jar'
                        }
                    }
                }
            }
        }

        stage('End to End Tests') {
            parallel {
                stage('Integration Tests') {
                    steps {
                        sh 'mvn --version'
                    }
                }
                stage('Performance Tests') {
                    steps {
                        sh 'mvn --version'
                    }
                }
                stage('Acceptance Tests') {
                    steps {
                        sh 'mvn --version'
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}
