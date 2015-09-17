#!/bin/bash

# This is used for ubunto 12.04 LTS

# Configuration jdk
export JAVA_HOME="/usr/local/java-8-oracle/jre"
export PATH="$JAVA_HOME/bin:$PATH"

# Configuration Maven
export MAVEN_HOME="/opt/mvn/3.3.3"
export PATH="$MAVEN_HOME/bin:$PATH"

# Configuraton Jacorb
export JACORB_HOME="$(cd ../vendor/jacorb-3.6.1; pwd)"
export PATH="$JACORB_HOME/bin:$PATH"

# Jacorb configuration files
export JACORB_CONFIGS_PATH="$(cd ../config/remote; pwd)"
