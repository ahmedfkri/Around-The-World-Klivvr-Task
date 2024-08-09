# Around The World

Around The World is an Android application designed to search for cities and display their locations on Google Maps. The app is built using the MVVM architecture to ensure maintainability and scalability.

## Features

- **Search Cities:** Efficient search through a list of cities using a Trie data structure.
- **View City Locations:** Display the selected city's location on Google Maps with a marker.
- **Responsive UI:** The UI automatically updates based on the search results.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Architecture](#architecture)
- [Screenshots](#screenshots)
- [Dependencies](#dependencies)
- [License](#license)

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/city-finder.git
    cd city-finder
    ```

2. Open the project in Android Studio.
3. Add your Google Maps API Key to the `AndroidManifest.xml` file:

    ```xml
    <meta-data
        android:name="com.google.android.geo.API_KEY"
        android:value="YOUR_API_KEY"/>
    ```

4. Build and run the project on an Android device or emulator.

## Usage

1. On the main screen, type a city name in the search bar to filter the list of cities.
2. Tap on a city to view its location on Google Maps.

## Architecture

The project follows the MVVM (Model-View-ViewModel) architecture:

- **Model:** Contains data classes like `City` and `Coord`, and the Trie data structure for efficient searching.
- **ViewModel:** Manages UI-related data in a lifecycle-aware manner, using `LiveData` to update the UI.
- **View:** Activities and Adapters display the data and handle user interactions.

## Package Structure

