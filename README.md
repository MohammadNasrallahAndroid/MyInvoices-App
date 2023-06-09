﻿# MyInvoices-App
-----------------------------------------------------------
MyInvoices is a simple Android application that allows users to manage their customers and invoices. The app provides user authentication, customer management, and invoice management functionalities.

-----------------------------------------------------------
Table of Contents
``````````````````
Getting Started
Prerequisites
Building and Running the Application
Chosen Technology and Its Benefits

-----------------------------------------------------------
Getting Started
````````````````
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

Prerequisites
``````````````
To build and run the MyInvoices app, you will need:

Android Studio (latest version)
Java Development Kit (JDK)
Android SDK
Building and Running the Application
Clone the repository or download the project files to your local machine.
Open Android Studio and select "Open an existing Android Studio project".
Navigate to the project directory and click "Open".
Wait for Android Studio to sync the project files and download any missing dependencies.
Connect an Android device or start an Android emulator.
Click the "Run" button (green triangle) in the Android Studio toolbar to build and run the app.
The app will be installed on your device or emulator, and you can interact with it.
Chosen Technology and Its Benefits
Java
The MyInvoices app is built using Java, a popular programming language for Android app development. Java is known for its platform independence, allowing developers to write code once and run it on multiple platforms with minimal modifications. It also provides a robust ecosystem and a vast number of libraries, which simplifies app development.

SQLite
```````
SQLite is used as the mobile database for the MyInvoices app. SQLite is a lightweight, serverless, and self-contained database that is easy to set up and requires minimal maintenance. It is an excellent choice for mobile apps that need to store data locally on the device. SQLite offers good performance, reliability, and a simple API to interact with the database.

Android Jetpack Components
```````````````````````````
The app uses Android Jetpack components, such as ViewModel, LiveData, and Data Binding, to ensure a more robust and maintainable codebase. Jetpack components help developers follow best practices, reduce boilerplate code, and simplify complex tasks.

ViewModel: The ViewModel class allows data to be separated from the UI components and persist configuration changes such as screen rotations. This separation of concerns results in a more manageable and testable codebase.
LiveData: LiveData is an observable data holder class that is lifecycle-aware. It allows UI components to observe changes in the data without creating explicit, rigid dependency paths between components. LiveData ensures that the UI is always up-to-date with the latest data while preventing memory leaks and crashes.
Data Binding: The Data Binding Library allows developers to bind UI components directly to data sources, reducing the need for boilerplate code to connect the UI with the data.

CLEAN Architecture
```````````````````
The MyInvoices app is built using CLEAN architecture principles. CLEAN architecture aims to separate an application into distinct layers, making it more modular, testable, and maintainable. The key layers in the app are:

Data Layer: This layer is responsible for handling the app's data, including database operations and data retrieval. In the MyInvoices app, the data layer is represented by the InvoiceRepository interface and its implementation (InvoiceRepositoryImpl).

Domain Layer: This layer contains the app's core business logic and is independent of the data layer and the presentation layer. In the MyInvoices app, the domain layer is represented by the Invoice and Customer classes.

Presentation Layer: This layer is responsible for displaying the data and handling user interactions. In the MyInvoices app, the presentation layer is represented by the ViewModel classes (InvoicesViewModel) and the activities (InvoicesActivity).

By employing the CLEAN architecture, the MyInvoices app ensures a clear separation of concerns, resulting in a more organized, maintainable, and testable codebase. Each layer can be developed, tested, and modified independently, improving the overall efficiency of the development process and the quality of the end product.

By utilizing these technologies, the MyInvoices app ensures a smooth and efficient development experience, maintainable codebase, and high-quality end product for users.

-----------------------------------------------------------
Author
```````
Mohammad Nasrallah - Initial work, development, and documentation.
