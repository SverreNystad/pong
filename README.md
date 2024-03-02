# The Game of Pong
<div align="center">

![CI](https://img.shields.io/github/actions/workflow/status/SverreNystad/pong/ci.yml)
![CodeQL](https://img.shields.io/github/actions/workflow/status/SverreNystad/pong/codeql.yml)
![GitHub top language](https://img.shields.io/github/languages/top/SverreNystad/pong)
![GitHub language count](https://img.shields.io/github/languages/count/SverreNystad/pong)
[![Project Version](https://img.shields.io/badge/version-1.0-blue)](https://img.shields.io/badge/version-1.0-blue)

</div>

This is a simple game of pong created using the LibGDX framework.

![Gameplay](docs/images/gameplay.png)
![Game over](docs/images/gameover.png)

## Installation and Setup

### Prerequisites
<ul>
<details> <summary><b> Git </b> </summary>
  Git is a distributed version control system that is used to manage the source code of this project. It is essential for cloning the project from GitHub and collaborating with other developers.

  * Git - Version Control System
    * Download and install Git from the official [Git website](https://git-scm.com/downloads).
    * After installation, verify the installation by running ```git --version``` in your command line or terminal.
</details>
</ul>

<ul>
  <details> <summary><b> Java JDK 17 (Download from Oracle's website) </b></summary>
  This project requires Java JDK to be installed. The project is tested with JDK 17.

  * Java JDK 17 - Java Development Kit is essential for compiling and running Java applications.
    * Download and install it from [Oracle's Java JDK Download Page](https://www.oracle.com/java/technologies/downloads/#java17) or adopt an open-source JDK like AdoptOpenJDK.
    * After installation, verify the installation by running ```java -version``` and ```javac -version``` in your command line or terminal.
  </details>
</ul>
<ul>
  <details> 
  <summary><b> Gradle 6.7 </b></summary>
  Gradle is used as the build tool for this project. It automates the process of building, testing, and deploying the application.

  * Gradle 6.7 - Gradle brings advanced build toolkit to manage dependencies and other aspects of the build process.
    * You can download Gradle from the [Gradle Download Page](https://gradle.org/install/).
    * Alternatively, if you are using a Gradle Wrapper script (gradlew or gradlew.bat), you do not need to manually install Gradle, as the wrapper script will handle the installation for you.
    * To confirm that Gradle is properly installed, run ```gradlew -v``` in your command line or terminal which will display the installed Gradle version.
  </details>
</ul>

<ul>
  <details> 
    <summary><b>Android SDK (if you want to run the game on Android)</b></summary>
    When testing the Android app one can run it either by connecting your Android phone via USB to your computer, or you could use an Android emulator (virtual device). To do this, you need to have the Android SDK installed. 
</ul>
Ensure that both Java and Gradle are properly installed and configured in your system's PATH environment variable for being able to run the game.

### Installing
To install the project, you can use the following commands:
```cmd
git clone https://github.com/SverreNystad/pong.git
```

## How to Play
The game can be played on both desktop and Android.

To start the game, you can start it on desktop using the following command:
```cmd
gradlew desktop:run
```

To start the game on Android, you can use the following command:
```cmd
gradlew android:run
```

## Acknowledgements
All the Asset are created by Esoe B.Studios. 

YouTube: https://www.youtube.com/channel/UCCsuCNggL9EanhfPgvjyGEA
Github: https://www.github.com/myebstudios
Twitter: https://www.twitter.com/myebstudios
Facebook: https://www.facebook.com/myebstudios

Thanks for downloading this asset, Enjoy!! ✌️👨🏽