#Build entities first
cd Artifacts/Entities/
mvn clean install
cd ..

#Build Cuisine
cd Cuisine/
mvn clean install
cd ..

#Build RegistreClient
cd RegistreClient/
mvn clean install
cd ..

#build j2e
cd ../j2e
mvn clean install
cd ../Artifacts/

#build Achat
cd Achat/
mvn clean install
cd ../..



