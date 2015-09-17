# log720-lab1

## Installation Mac OS X

```sh
# Clone the repository
git clone https://github.com/maximebedard/log720-lab1
cd log720-lab1

# Install dependencies
brew install maven wget git
wget http://www.jacorb.org/releases/3.6.1/jacorb-3.6.1-binary.zip -P ~/Downloads
unzip -q ~/Downloads/jacorb-3.6.1-binary.zip -d ./vendor

# Install old shit dependencies (does not work)
# brew install ant
# wget http://www.jacorb.org/releases/2.3.1/jacorb-2.3.1-src.zip -P ~/Downloads
# unzip -q ~/Downloads/jacorb-2.3.1-src.zip -d ./vendor

# Running the client on MAC OS X
cd bin/
source ./bash_profile
./bureau.sh
./voiture.sh

# Copy shit over to the server
rsync -azh ./ AM05130@log720.logti.etsmtl.ca:~/lab1

# Running the naming app and the server
ssh AM05130@log720.logti.etsmtl.ca
cd bin/
source ./bashrc
./naming.sh
./serveur.sh
```
