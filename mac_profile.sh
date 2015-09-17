#!/usr/local/bin/bash

# This is used for MAC OS X Yosemite
export LOG720_LAB1_PROJECT_ROOT=$PWD

# Configuration jdk
export JAVA_HOME="$(/usr/libexec/java_home -v 1.8)"
export PATH="$JAVA_HOME/bin:$PATH"

# Configuration Maven
export MAVEN_HOME="/usr/local/Cellar/maven/3.3.3/libexec"
export PATH="$MAVEN_HOME/bin:$PATH"

# Configuraton Jacorb
export JACORB_HOME="$(cd $LOG720_LAB1_PROJECT_ROOT/vendor/jacorb-3.6.1; pwd)"
export PATH="$JACORB_HOME/bin:$PATH"

# Jacorb configuration files
export JACORB_CONFIGS_PATH="$(cd $LOG720_LAB1_PROJECT_ROOT/config/local; pwd)"
