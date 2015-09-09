@echo off
::
:: Script pour "epurer" les variables d'environnement PATH et CLASSPATH
:: et demarrer une coquille DOS utilisable pour executer les exemples
:: fournis avec le cours et realiser les laboratoires du cours.
::
:: Destine aux etudiants de l'ETS. Conseil: copiez ce script a un endroit ou
:: vous avez le plein controle et creez un raccourci vers celui-ci sur votre
:: "bureau" Windows, ce sera utile durant les laboratoires tout au long de la
:: session.
::
:: Tous les repertoires utilises dans ce script refletent l'installation des
:: logiciels dans les laboratoires du Departement.
::
:: Le script est divise en sections car utilise dans plusieurs cours. Les
:: sections non pertinentes pour un cours donne peuvent etre retirees ou mises
:: en commentaire. Le script regle dans l'ordre les parametres lies aux
:: logiciels suivants:
::
:: 1 - le script lui-meme;
:: 2 - le SDK java;
:: 3 - une distribution de Apache Maven;
:: 4 - une distribution de JacORB.
::
:: Enfin, le script demarre une coquille (shell) DOS dans un repertoire
:: specifique. Cette coquille peut alors etre utilisee pour compiler les projets
:: java (via javac ou Maven) lies au cours. Cette coquille peut egalement etre
:: utilisee pour demarrer des applications CORBA developpees en java avec JacORB.
::
:: Par Roger Champagne, P.Eng., Ph.D.
::     2008-Sep-08
:: MAJ 2015-Aug-28

:: 1 - Parametres globaux ======================================================

:: Racine du lecteur ou ce script est stocke. Pour utilisation dans les
:: laboratoires de l'ETS, il est suggere d'utiliser votre lecteur reseau (J:) ou
:: une barette de memoire USB (typiquement E:). Sur votre ordinateur personnel,
:: C: est la valeur typique.
set CONFIG_DRIVE=j:

:: Repertoire ou l'on souhaite stocker le present script
set CONFIG_HOME=%CONFIG_DRIVE%\log720

:: Ecrasement du path, seul le repertoire ci-haut est conserve. Il est
:: fortement recommande d'ecraser le PATH par defaut sur les postes du lab
:: a l'ETS, car cette variable est surchargee et contient parfois des entrees
:: qui causent des problemes dans ce cours (conflits de versions, etc).
set path=%CONFIG_HOME%

:: 2 - Reglages SDK java =======================================================

:: Repertoire du SDK Java a utiliser
set JAVA_HOME=C:\oracle\java\jdk7_71_x64

:: path: ajout des repertoires pertinents pour le SDK java.
set path=%PATH%;%JAVA_HOME%\bin

:: Ecrasement du classpath, seul le repertoire courant est conserve. Il est
:: fortement recommande d'ecraser le CLASSPATH par defaut sur les postes du lab
:: a l'ETS, car cette variable est surchargee et contient parfois des entrees
:: qui causent des problemes dans ce cours (conflits de versions, etc).
set classpath=.

:: 3 - Reglages Maven ==========================================================

:: Repertoire de la distribution maven a utiliser
set MVN_HOME=c:\apache\maven\3.2.2

:: Repertoire ou les utilitaires Maven du cours sont stockes
set MY_MVN_HOME=%CONFIG_HOME%

:: path: ajout des repertoires pertinents pour Maven.
set path=%PATH%;%MVN_HOME%\bin;%MY_MVN_HOME%

:: 4 - Reglages JacORB =========================================================

:: Repertoire d'intallation de JacORB
set JACORB_HOME=C:\jacorb\2.3.1_x64

:: Endroit ou vous stockez orb.properties et jacorb.properties. Est ajoute dans
:: le classpath plus bas. j:\ est un endroit plausible et pratique dans les labs
:: de l'ETS. 
set JACORB_PROPERTIES_DIR=%CONFIG_HOME%

:: path: ajout de la distribution JacORB
set path=%PATH%;%JACORB_HOME%\bin

:: classpath: ajout du reprtoire ou sont stockes les fichiers de config JacORB 
set CLASSPATH=%JACORB_PROPERTIES_DIR%;%classpath%

:: 5 - Demarrage d'une coquille (shell) a un endroit predetermine ==============

:: Lecteur et repertoire d'ou l'on veut demarrer la coquille.
:: Modifier selon vos besoins.
set START_DRIVE=u:
set START_DIR=\log720\grid_rc_modules\scripts\dos

%START_DRIVE%
call C:\WINDOWS\system32\cmd.exe /k cd %START_DIR%
