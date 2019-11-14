# Install package to be available to add key to repo
apt install gpg-agent -y

# Install curl program to do http request 
apt-get install curl -y 

# check for existing link to cassandra in source list
if ! ([ -f /etc/apt/sources.list.d/cassandra.sources.list ] && grep -Fq "deb http://www.apache.org/dist/cassandra/debian 311x main" /etc/apt/sources.list.d/cassandra.sources.list); then
    echo "deb http://www.apache.org/dist/cassandra/debian 311x main" | tee /etc/apt/sources.list.d/cassandra.sources.list
fi

curl https://www.apache.org/dist/cassandra/KEYS | apt-key add -

apt-get update

# Remove if cassandra already exists to be sure for install newly and new version
apt-get purge -y cassandra

# Install cassandra
apt-get install -y cassandra


