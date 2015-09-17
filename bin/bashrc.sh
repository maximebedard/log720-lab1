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

# Configuration classpath
export CLASSPATH="$JACORB_CONFIGS_PATH"

export NAMING_SERVICE_HOST=log720.logti.etsmtl.ca
export NAMING_SERVICE_PORT=31501

export APP_VOITURE=ca.etsmtl.log720.lab1.ClientVoiture
export APP_VOITURE_CLASSPATH="$(cd ../app-bureau/target/classes; pwd)"

export APP_BUREAU=ca.etsmtl.log720.lab1.ClientBureau
export APP_BUREAU_CLASSPATH="$(cd ../app-bureau/target/classes; pwd)"

export APP_SERVEUR=ca.etsmtl.log720.lab1.Serveur
export APP_SERVEUR_CLASSPATH="$(cd ../app-serveur/target/classes; pwd)"

export APP_IDL_CLASSPATH="$(cd ../app-idl/target/classes; pwd)"

export CLASSPATH=$APP_VOITURE_CLASSPATH:$APP_BUREAU_CLASSPATH:$APP_SERVEUR_CLASSPATH:$APP_IDL_CLASSPATH:$CLASSPATH
