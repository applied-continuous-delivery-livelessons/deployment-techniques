FROM jenkins/jenkins:2.71

USER root

# Install all necessary software
RUN apt-get update && apt-get install -y ruby curl

# Let's assume that we don't want to have the setup wizard
# Nor do we want to reject any parameters passed between jobs
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false \
              -Djava.awt.headless=true \
              -Dhudson.model.ParametersAction.keepUndefinedParameters=true

USER jenkins

# Install plugins
# JENKINS_HOST=admin:7de46892017e4a7a9da3cefae9fd4671@localhost:49001
#  curl -sSL "http://$JENKINS_HOST/pluginManager/api/xml?depth=1&xpath=/*/*/shortName|/*/*/version&wrapper=plugins" | perl -pe 's/.*?<shortName>([\w-]+).*?<version>([^<]+)()(<\/\w+>)+/\1 \2\n/g'|sed 's/ /:/'
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# Automatic job setup
COPY init.groovy /usr/share/jenkins/ref/init.groovy
COPY jobdsl-pipeline.groovy /usr/share/jenkins/jobdsl-pipeline.groovy
