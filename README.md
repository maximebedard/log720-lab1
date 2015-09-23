# log720-lab1

## Prérequis

- [x] Installer JDK 8
- [x] Installer Maven
- [x] Cloner le repository (https://github.com/maximebedard/log720-lab1)

## Installation (Mac OS X)

__dépendances__

```sh
# Vendor Jacorb
wget http://www.jacorb.org/releases/3.6.1/jacorb-3.6.1-binary.zip -P ~/Downloads
unzip -q ~/Downloads/jacorb-3.6.1-binary.zip -d ./vendor
```

__exécution__

```sh
source ./mac_profile.sh
mvn clean compile

# Client
./bin/bureau.sh
./bin/voiture.sh

# Serveur
# Enlever les commentaires au lignes 30,31 du fichier config/local/jacorb.properties
# Commenter la ligne 27 du fichier config/local/jacorb.properties
./bin/naming.sh
./bin/serveur.sh
```

## Installation (Ubuntu log720.logti.etsmtl.ca)

__dépendances__

```sh
# Vendor Jacorb
# noop

# Déploiment des fichiers sur le serveur Ubuntu
rsync -azh ./ AM05130@log720.logti.etsmtl.ca:~/lab1
ssh AM05130@log720.logti.etsmtl.ca
```

__exécution__

```sh
# Execution du serveur sur Ubuntu
source ./ubuntu_profile.sh
mvn clean compile
./bin/naming.sh
./bin/serveur.sh
```

## Installation (Windows)

__dépendances__

```powershell
Invoke-WebRequest "http://www.jacorb.org/releases/3.6.1/jacorb-3.6.1-binary.zip" -OutFile ~\Downloads
# TODO unzip
```

__exécution__

```powershell
call windows_profile.bat
mvn clean compile

# Client
.\bin\bureau.bat
.\bin\voiture.bat

# Serveur
# Enlever les commentaires au lignes 30,31 du fichier config/local/jacorb.properties
# Commenter la ligne 27 du fichier config/local/jacorb.properties
.\bin\naming.bat
.\bin\serveur.bat
```
