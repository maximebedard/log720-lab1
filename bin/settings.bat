set NAMING_SERVICE_HOST=localhost
set NAMING_SERVICE_PORT=31501

set APP_VOITURE=ca.etsmtl.log720.lab1.ClientVoiture
set APP_VOITURE_CLASSPATH=%LOG720_LAB1_PROJECT_ROOT%\app-bureau\target\classes

set APP_BUREAU=ca.etsmtl.log720.lab1.ClientBureau
set APP_BUREAU_CLASSPATH=%LOG720_LAB1_PROJECT_ROOT%\app-bureau\target\classes

set APP_SERVEUR=ca.etsmtl.log720.lab1.Serveur
set APP_SERVEUR_CLASSPATH=%LOG720_LAB1_PROJECT_ROOT%\app-serveur\target\classes

set APP_IDL_CLASSPATH=%LOG720_LAB1_PROJECT_ROOT%\app-idl\target\classes

set CLASSPATH=%JACORB_CONFIGS_PATH%
set CLASSPATH=%APP_VOITURE_CLASSPATH%;%APP_BUREAU_CLASSPATH%;%APP_SERVEUR_CLASSPATH%;%APP_IDL_CLASSPATH%;%CLASSPATH%
