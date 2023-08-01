# List of Dog Breeds - Android Application

![Project Logo](https://www.dogalize.com/wp-content/uploads/2016/12/African-wild-dog.jpg)

## Introduction

This is an Android application developed using Kotlin programming language, which allows users to explore a list of dog breeds, mark breeds as favorites, and view breed details along with images. The application follows the MVVM clean architectural pattern, ensuring a clear separation of concerns and promoting maintainability, testability, and scalability.

## Features

- Display a list of dog breeds in the list ordered alphabetically by breed name (A-Z)
- The user can mark their favorite breeds by clicking on the heart button on the list and see only those breeds in another list
- The user can see the detailed information about a selected breed, including an image if user click on an item (user will be redirected to the details screen).


### Some Other Features

- Images are cached, so if an image is loaded from the server, it will be available offline for future use.
- Some animations are implemented in this project, e.g., animation on screen changes, animation on items loaded in the list, animation on list scrolling, and animation on image display.
- Some unit tests are written, e.g., unit tests for view models and the repository.

## Screenshots

_Insert relevant screenshots of the app here, showcasing its features._

## Project Structure

The project adheres to a structured organization to keep the codebase clean and maintainable. Here is an overview of the main directories:

    - app
      |- src
        |- main
          |- java/me.mafu.doglist_simplesurance
            |- data
              |- remote
              |- local
              |- repositories
            |- di
            |- domain
              |- models
              |- repositories
              |- usecases
            |- presentation
              |- adapter
              |- ui
              |- viewmodels


- **Presentation Layer:** This layer is responsible for handling the UI components and user interactions. It contains Activities, Fragments, ViewModels and Adapters. ViewModel is used to retain and manage UI-related data across configuration changes. The layer is built using Android's Jetpack components, such as ViewModel and Navigation, to ensure a robust and consistent user experience.


- **Domain Layer:** The domain module represents the core business logic of the application. It contains entities (model) and use cases that are used by the presentation layer to communicate with the data layer and this layer also contains interfaces for the repositories. This layer is independent of the Android framework and can be easily tested in isolation.


- **Data Layer:** The data module handles data retrieval and manipulation. It includes implementations of the interfaces defined in the domain layer, as well as data sources like Room (local database) and Retrofit (remote API). The data layer is responsible for data caching, fetching data from remote sources, and providing the required data to the domain layer.


- **di**: Contains the Hilt components for dependency injection.


## Libraries and Dependencies

The project utilizes several popular libraries to enhance its functionality:

- **ViewModel:** Part of Android Jetpack, ViewModel helps manage UI-related data and lifecycle changes efficiently.


- **Kotlin Coroutine:** Provides a lightweight and efficient way to perform asynchronous operations in Kotlin.


- **Retrofit:** A type-safe HTTP client for Android, Retrofit simplifies network communication with APIs.


- **okhttp3:logging-interceptor:** A library for logging HTTP request and response.


- **Gson:** A library for parsing JSON


- **Room:** Room is an abstraction layer over SQLite, providing a robust database solution for Android applications.


- **Hilt:** Hilt is a dependency injection library for Android that simplifies dependency management.


- **Navigation:** Android Navigation component facilitates navigation between different screens and simplifies fragment transactions.


- **Glide:** A powerful image loading and caching library for Android.


- **JUnit:** The standard framework for writing unit tests in Java and Kotlin.


- **Coroutine Test:** Provides testing utilities to test coroutines in a controlled environment.


- **Mock:** A mocking library, which helps create mock objects for unit testing.

## How to Use

1. Clone the repository to your local machine using the following command:

   https://github.com/mafujuls/doglist_simplesurance.git


2. Open the project in Android Studio.

3. Build and run the application on an Android emulator or physical device.

## Acknowledgments
### Special hanks to [dog.ceo](https://dog.ceo/dog-api/documentation/) for providing the nice API

Thanks to the developers and contributors of the libraries used in this project, as well as the Android community for their valuable resources and support.
