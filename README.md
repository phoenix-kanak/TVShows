# TV Shows App

This is an Android application that fetches TV show data from the [Episodate API](https://www.episodate.com/api) and displays it in a user-friendly format. The app includes features like searching for shows and navigating to detailed show information.

## Architecture
The app follows the **MVVM (Model-View-ViewModel)** architecture pattern to makes the code more modular and maintainable.

- **Model**: Manages the data from the Episodate API.
- **ViewModel**: Acts as the mediator between the View and Model, holding and preparing data for the UI.
- **View**: The UI components, including RecyclerView for lists and Activities for screens.

### Libraries Used
- **Retrofit**: For networking and fetching data from the Episodate API.
- **Picasso**: For loading and displaying images.
- **RecyclerView**: For displaying lists of TV shows.
- **LiveData & ViewModel**: To observe and manage UI-related data.

### Features
- Fetches a list of TV shows from the Episodate API.
- Allows users to search for TV shows.
- Displays detailed information about selected shows.
  
### Setup Instructions
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/tv-shows-app.git
    ```
2. Open the project in **Android Studio**.
3. Run the app on an emulator or a physical device.

### How It Works
- The app fetches data from the Episodate API using Retrofit.
- The fetched data is observed using LiveData in ViewModels and displayed in RecyclerViews.
- Picasso is used to load show images efficiently.
