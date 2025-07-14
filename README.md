# **🌦️ Clime Weather App**

*A beautiful, modern weather forecast app for Android, built with Java and the OpenWeatherMap API.*

<img width="1080" height="2412" alt="image" src="https://github.com/user-attachments/assets/3c38804c-538d-48a9-a990-e371f9c45048" />


## **✨ Features**

* **Current Weather:** Get instant access to the temperature, city name, and weather conditions for any city.  
* **City Search:** Easily search for any city worldwide to get its weather information.  
* **5-Day Forecast:** View a 5-day weather forecast with daily high/low temperatures and weather icons.  
* **Sleek UI:** A clean, modern, and visually appealing user interface inspired by the latest design trends.

## **🛠️ Tech Stack & Libraries**

This project is built with a modern Android tech stack:

* **Language:** Java  
* **Architecture:** Standard Android Activity/View structure  
* **API:** [OpenWeatherMap API](https://openweathermap.org/api) for weather data.  
* **Networking:** [Retrofit 2](https://square.github.io/retrofit/) for type-safe HTTP calls to the API.  
* **JSON Parsing:** [Gson](https://github.com/google/gson) for seamlessly converting JSON data into Java objects.  
* **Image Loading:** [Glide](https://github.com/bumptech/glide) for efficiently loading and caching weather icons.  
* **UI Components:** RecyclerView and CardView for displaying forecast lists.

## **🚀 Setup and Installation**

To get this project running on your own machine, follow these steps:

1. **Clone the repository:**  
   git clone https://github.com/your-username/Clime-Android-App.git

2. Open in Android Studio:  
   Open the cloned directory in Android Studio. It should automatically sync the Gradle project.  
3. **Get an API Key:**  
   * Go to [OpenWeatherMap](https://openweathermap.org/) and sign up for a free account.  
   * Navigate to the "API keys" tab and copy your key.  
4. **Add the API Key:**  
   * Open the MainActivity.java file.  
   * Find the API\_KEY constant and replace the placeholder with your own key:  
     private static final String API\_KEY \= "YOUR\_API\_KEY\_HERE";

5. **Build and Run:**  
   * Build the project and run it on an Android emulator or a physical device.

## **🔮 Future Improvements**

This project has a solid foundation. Future features could include:

*  **GPS Location:** Automatically detect the user's current location on startup.  
*  **Detailed Weather Info:** Add more data like humidity, wind speed, and "feels like" temperature.  
*  **History:** Save recent searches for quick access.  
*  **UI Enhancements:** Add animations and transitions for a more dynamic user experience.
