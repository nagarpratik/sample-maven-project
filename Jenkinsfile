@Library("sfci-pipeline-sharedlib@master") _
import net.sfdc.dci.BuildUtils
import net.sfdc.dci.MavenUtils

def envDef = [
    buildImage : "ops0-artifactrepo1-0-prd.data.sfdc.net/commercecloud/cc-build-container",
    stopSuccessEmail: true]

env.SFCI_HOST='ops0-artifactrepo1-0-prd.data.sfdc.net'
env.no_proxy='127.0.0.1, localhost'
env.GUS_TEAM_NAME= 'TODO: I will be a good citizen and change this to a proper GUS team name later!'
env.CODE_COVERAGE_THRESHOLD = 1;


def tag_version(version) {
  echo "Tagging version on GitHub: v0.0.1-SNAPSHOT"
  sh "git tag -s v0.0.1-SNAPSHOT"
  sh "git push origin master --tags"
}

executePipeline(envDef) {

  stage('Init') {

    checkout scm

    withCredentials([usernamePassword(credentialsId: 'sfci-docker', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
       sh "docker login -u '${DOCKER_USERNAME}' -p '${DOCKER_PASSWORD}' ops0-artifactrepo1-0-prd.data.sfdc.net"
    }

    def commitSha = BuildUtils.getLatestCommitSha(this)
    echo "Running commit ${commitSha} on build ${env.BUILD_ID}"

    mavenInit()
  }

  def pom = this.readMavenPom()
  def artifactId = pom.artifactId
  def artifactVersion = pom.version
  def artifactName = "sample-rest-springboot-${artifactVersion}.jar"
  def dockerNameLowerCase = "sample-rest-springboot"

  stage('Build Artifacts') {

    def maven_args = "-DskipITs"

    if (artifactVersion.endsWith("SNAPSHOT")) {
      maven_args = "${maven_args} -Pnexus-snapshots"
    }

    mavenBuild([
        maven_args: maven_args,
        maven_goals: "clean install"
    ])
  }

  stage('Build Image') {
    sh "docker build --build-arg PRD_PROXY=${HTTP_PROXY} --build-arg JAR_FILE=${artifactName} --build-arg JAVA_OPTS=-Xmx2048m  -t sample-rest-springboot:${artifactVersion} ."
  }
  
  stage ('Integration Test') {
      // This is a Broad Integration Test
      try {
        // start up docker-compose with depedencies
        sh "docker-compose -f ./docker-compose.yaml up &"
        
        // Skip Unit Tests and don't skip ITs
        mavenBuild([maven_args: '-DskipUTs=true -DskipITs=false', maven_goals: 'verify'])
       // sh "mvn -B verify -DskipUTs=true -DskipITs=false --settings .m2/settings.xml"
      }
      finally {
       // shut down docker-compose with dependencies
        sh "docker-compose -f ./docker-compose.yaml down"
      }
  }

  stage('Push Image') {
    sh "docker tag sample-rest-springboot:${artifactVersion} ops0-artifactrepo1-0-prd.data.sfdc.net/commercecloud/sample-rest-springboot:${artifactVersion}"
    sh "docker push ops0-artifactrepo1-0-prd.data.sfdc.net/commercecloud/sample-rest-springboot:${artifactVersion}"
  }
}
