# log720-lab1

## Installation

```sh
# Clone the repository
git clone https://github.com/maximebedard/log720-lab1
cd log720-lab1

# Installation des dépendances sur Mac OS X
brew install maven wget git
wget http://www.jacorb.org/releases/3.6.1/jacorb-3.6.1-binary.zip -P ~/Downloads
unzip -q ~/Downloads/jacorb-3.6.1-binary.zip -d ./vendor

# Installation des dépendances sur Ubuntu
# NOOP

# Execution du client sur Mac OS X
source ./mac_profile.sh
mvn clean compile
./bin/bureau.sh
./bin/voiture.sh

# Execution du serveur sur Mac OS X
# Enlever les commentaires au lignes 30,31 du fichier config/local/jacorb.properties
# Commenter la ligne 27 du fichier config/local/jacorb.properties
./bin/naming.sh
./bin/serveur.sh

# Déploiment des fichiers sur le serveur Ubuntu
rsync -azh ./ AM05130@log720.logti.etsmtl.ca:~/lab1
ssh AM05130@log720.logti.etsmtl.ca

# Execution du serveur sur Ubuntu
source ./ubuntu_profile.sh
mvn clean compile
./bin/naming.sh
./bin/serveur.sh
```

# Troubleshooting

```
java.io.FileNotFoundException: ~/public_html/NS_Ref (No such file or directory)
  at java.io.FileOutputStream.open0(Native Method)
  at java.io.FileOutputStream.open(FileOutputStream.java:270)
  at java.io.FileOutputStream.<init>(FileOutputStream.java:213)
  at java.io.FileOutputStream.<init>(FileOutputStream.java:101)
  at org.jacorb.naming.NameServer.main(NameServer.java:320)
  java.lang.RuntimeException: ~/public_html/NS_Ref (No such file or directory)
```

__solution__
```sh
touch ~/public_html/NS_Ref
```
