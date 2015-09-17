export NAMING_SERVICE_HOST=localhost
export NAMING_SERVICE_PORT=31501

export APP_VOITURE=ca.etsmtl.log720.lab1.ClientVoiture
export APP_VOITURE_CLASSPATH="$(cd ../app-bureau/target/classes; pwd)"

export APP_BUREAU=ca.etsmtl.log720.lab1.ClientBureau
export APP_BUREAU_CLASSPATH="$(cd ../app-bureau/target/classes; pwd)"

export APP_SERVEUR=ca.etsmtl.log720.lab1.Serveur
export APP_SERVEUR_CLASSPATH="$(cd ../app-serveur/target/classes; pwd)"

export APP_IDL_CLASSPATH="$(cd ../app-idl/target/classes; pwd)"

export CLASSPATH="$JACORB_CONFIGS_PATH"
export CLASSPATH=$APP_VOITURE_CLASSPATH:$APP_BUREAU_CLASSPATH:$APP_SERVEUR_CLASSPATH:$APP_IDL_CLASSPATH:$CLASSPATH
