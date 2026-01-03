# Nexus Time Management System

A simple Java-based time management system that tracks user activities and provides static visualizations to help you understand how you spend your time.

## Features

- Track and manage time spent on various activities
- Generate static visualizations of your time usage
- Simple and lightweight Java application
- Easy-to-use build system

## Project Structure

```
Nexus_TManagement_System/
├── src/              # Source code files
├── scripts/          # Build and utility scripts
├── META-INF/         # Manifest and metadata files
├── build/            # Compiled class files (generated)
├── bin/              # JAR executable (generated)
└── README.md         # This file
```

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Bash shell (for running build scripts)

## Building the Project

To build the project, run the build script from the project root:

```bash
./scripts/build
```

This script will:
- Compile all Java source files from `src/` into the `build/` directory
- Package the compiled classes into a JAR file in the `bin/` directory

## Running the Application

After building, you can run the application using:

```bash
java -jar bin/Nexus_TManagement_System.jar
```

## Customization

You can modify the `META-INF/MANIFEST.MF` file to customize the JAR metadata based on your source code structure. Update the Main-Class entry to match your main application class.

## Contributing

Feel free to clone this repository and make improvements:

```bash
git clone <repository-url>
cd Nexus_TManagement_System
```
