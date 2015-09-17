#!/usr/local/bin/bash

# This is used for MAC OS X Yosemite

# Configuration jdk
export JAVA_HOME="$(/usr/libexec/java_home -v 1.8)"
export PATH="$JAVA_HOME/bin:$PATH"

# Configuration Maven
export MAVEN_HOME="/usr/local/Cellar/maven/3.3.3/libexec"
export PATH="$MAVEN_HOME/bin:$PATH"

# Configuraton Jacorb
export JACORB_HOME="$(cd ../vendor/jacorb-3.6.1; pwd)"
export PATH="$JACORB_HOME/bin:$PATH"

# Jacorb configuration files
export JACORB_CONFIGS_PATH="$(cd ../config; pwd)"

# Configuration classpath
export CLASSPATH="$JACORB_CONFIGS_PATH"

export NAMING_SERVICE_HOST=localhost
export NAMING_SERVICE_PORT=31500

export APP_VOITURE=ca.etsmtl.log720.lab1.ClientVoiture
export APP_VOITURE_CLASSPATH="../app-bureau/target/classes"

export APP_BUREAU=ca.etsmtl.log720.lab1.ClientBureau
export APP_BUREAU_CLASSPATH="../app-bureau/target/classes"

export APP_SERVEUR=ca.etsmtl.log720.lab1.Serveur
export APP_SERVEUR_CLASSPATH="../app-serveur/target/classes"

export APP_IDL_CLASSPATH="../app-idl/target/classes"

export CLASSPATH=APP_VOITURE_CLASSPATH:APP_BUREAU:APP_SERVEUR:APP_IDL_CLASSPATH:$CLASSPATH
