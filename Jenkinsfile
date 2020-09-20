pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'

                // Run Maven on a Unix agent.
                sh "mvn clean install -DskipTests=true -Dmaven.javadoc.skip=true -B -V"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
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
                     //If Maven was able to run the tests, even if some of the test
                     //failed, record the test results and archive the jar file.
                        success {
                            junit '**/target/surefire-reports/TEST-*.xml'
                            archiveArtifacts 'target/*.jar'
                        }
                    }
                }
                stage('Static Analysis') {
                    steps {
                        sh 'mvn --version'
                        //sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar -Dsonar.projectKey=Mitschi_restservice-example'
                    }
                }
                stage('Mutation Testing') {
                    steps {
                        sh 'mvn clean test org.pitest:pitest-maven:mutationCoverage'
                    }
                    post {
                        //If Maven was able to run the tests, even if some of the test
                        //failed, record the test results and archive the jar file.
                        success {
                            pitmutation '**/target/pit-reports/**/mutations.xml'
                            archiveArtifacts 'target/*.jar'
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
            }
        }
        stage('Deploy') {
            steps {
                sh 'mvn --version'
            }
        }
    }
}
