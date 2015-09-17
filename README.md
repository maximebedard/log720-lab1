# log720-lab1

## Installation Mac OS X

```sh
# Clone the repository
git clone https://github.com/maximebedard/log720-lab1
cd log720-lab1

# Install dependencies (Mac)
brew install maven wget git
wget http://www.jacorb.org/releases/3.6.1/jacorb-3.6.1-binary.zip -P ~/Downloads
unzip -q ~/Downloads/jacorb-3.6.1-binary.zip -d ./vendor

# Running the client on MAC OS X
cd bin/
source ./mac_profile.sh
cd ../
mvn clean compile
./bureau.sh
./voiture.sh

# Copy shit over to the server
rsync -azh ./ AM05130@log720.logti.etsmtl.ca:~/lab1

# Running the naming app and the server
ssh AM05130@log720.logti.etsmtl.ca
cd bin/
source ./ubuntu_profile.sh
cd ../
mvn clean compile
./naming.sh
./serveur.sh
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
