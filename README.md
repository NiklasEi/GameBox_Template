# Template Game for GameBox

This repository can be used as a template for a [GameBox](https://www.spigotmc.org/resources/37273/) game. Simply click the `Use this template` button in the repository view to start your won GameBox game (see the [GitHub docs](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-from-a-template) for more info on templates). 

## General structure
* this is a maven project and has the default maven structure.
  * to easily fill the [module.yml](src/main/resources/module.yml) file with the correct data, the GameBox dependency is versioned in a property and a maven plugin is used to create timestamps for every build.

## What to change
1. Change the project information in [pom.xml](pom.xml)
    ```xml
    <groupId>me.nikl.gamebox.games</groupId>
    <artifactId>template</artifactId>
    <name>TemplateGame</name>
    <version>3.0.0</version>
    <packaging>jar</packaging>
    <description>Small template to help you write your own games</description>
    ```

2. Change the author name in [module.yml](src/main/resources/module.yml). All the other information will be automatically read from the `pom.xml` file.

3. Start writing your game ;)

## Test your module on your server

To install your module on a server, copy the compiled jar to the modules directory of GameBox. Then reload GameBox or your Minecraft server.

## Publish to the GameBox cloud

Once your game is done and tested on your own server you can publish it to the GameBox cloud. At this point everyone using GameBox will be able to see and install it.

*For the moment please contact Nikl on the GameBox support server. The publishing process is not user friendly yet.*
