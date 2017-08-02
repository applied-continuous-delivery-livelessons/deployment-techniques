import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.JobManagement
import javaposse.jobdsl.plugin.JenkinsJobManagement
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.impl.*
import hudson.model.*
import jenkins.model.*
import hudson.plugins.groovy.*
import net.sf.json.JSONObject

File jobScript = new File('/usr/share/jenkins/jobdsl-pipeline.groovy')
JobManagement jobManagement = new JenkinsJobManagement(System.out, [:], new File('.'))

println "Creating the seed job"
new DslScriptLoader(jobManagement).with {
    runScript(jobScript.text)
}

println "Adding credentials"
SystemCredentialsProvider.getInstance().getCredentials().add(
        new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL,
                "my_id", "my description", "user", "pass"))
SystemCredentialsProvider.getInstance().save()

println "Adding jdk"
Jenkins.getInstance().getJDKs().add(new JDK("jdk8", "/usr/lib/jvm/java-8-openjdk-amd64"))

println "Marking allow macro token"
Groovy.DescriptorImpl descriptor =
        (Groovy.DescriptorImpl) Jenkins.getInstance().getDescriptorOrDie(Groovy)
descriptor.configure(null, JSONObject.fromObject('''{"allowMacro":"true"}'''))