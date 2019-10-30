# Mancala MVC Java

Read this readme carefully before you start working on the Mancala MVC assignment.


Maven
-----

Use Maven to compile/build/run your project

==> [Maven Home](https://maven.apache.org/)

Guide to installing 3rd party JARs

==> [Maven 3rd Party JARs](https://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html)


Using the MancalaAPI project
----------------------------

The MancalaAPI has a dependency on the MancalaDomain so make sure you do the following:

- Implement the Mancala interface (Mancala.java) in your own Mancala Domain project, or
- Use the MancalaDomainForwardInitializationComplete project provided as a JAR

(both can be found in the root of this repo)

### Using the Mancala interface

Copy the Mancala interface into the right package within your domain project and make a class that implements this interface and calls your domein objects for the specified methods. Than run the command below from within your MancalaDomein project to install the MancalaDomain JAR into your local Maven repository so that the MancalaAPI project can find it:

    C\>mvn clean install

If you make a change in the MancalaDomain project rerun this step to make sure the change ends up in your local Maven repository.

### Using the MancalaDomainForwardInitializationComplete JAR

Check the version of your local *maven-install-plugin* with the following command:

    C\>mvn -Dplugin=org.apache.maven.plugins:maven-install-plugin help:describe

The version of your local *maven-install-plugin* should be 2.5 or higher. If this is not the case use the link below to download a newer version of this plugin

==> [Maven-Install-Plugin](https://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-install-plugin/)

Download both the JAR and the POM file and than use the following command to install this new version:

    C\>mvn install:install-file -Dfile=<path-to-file> -DpomFile=<path-to-pomfile>

PS: This wil only install a newer version of the maven-install-plugin to be used on the commandline, and does NOT automatically update Maven to use this new version of the maven-install-plugin (if you want Maven to use this newer version you need to configure Maven to use this newer version, which we won't further discuss here).

Go to the directory where you'll find the MancalaDomainForwardInitializationComplete JAR and run the following command to install this JAR:

    C\>mvn org.apache.maven.plugins:maven-install-plugin:3.0.0-M1:install-file -Dfile=MancalaDomain-1.0.0-SNAPSHOT.jar
    (this is for version *3.0.0-M1* of the *maven-install-plugin*)

Now the MancalaDomain should be installed in your local Maven repository so that the MancalaAPI project can find it.

### Run the MancalaAPI project

The HTTP port used in this project is defined within the projects POM, so if port 80 is already in use you can change the portnumber here. From within the MancalaAPI project run the following commands:

    C\>mvn clean package
    C\>mvn jetty:run

Now that the MancalaAPI project is running you can use Postman to test it or go to:

    http://localhost/mancala
	(add a portnumber to *localhost* if you've changed the port in the projects POM)

Using Postman
-------------

==> [Get Postman](https://www.getpostman.com/)

### POST request to initialize the game

    URL:    http://localhost/mancala/api/players
	Params:	{nameplayer1: "Bla", nameplayer2: "Bliep"}
	        (Body parameters 'application/json' encoded)

### PUT request to play the game

    URL:    http://localhost/mancala/api/play/{pit_index}
	Pit index:
	
           13 12 11 10  9  8
	    14                    7
	        1  2  3  4  5  6
