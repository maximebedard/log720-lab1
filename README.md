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

# Configure profile
source ~/scripts/log720_profile

# Run the ClientBureau app
mvn compile exec:clientBureau

# Run the ClientVoiture app
mvn compile exec:clientVoiture
```
