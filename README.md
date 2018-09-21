# FastEx
### Delivery tracker

This is a simple application to track pending deliveries

### What's in the App

This app consists of 3 screens. First screen is the splash screen which is displayed during the loading of components. After loading is complete, user is taken to the second screen where the list of pending deliveries is displayd. User can tap on any item to check the details of the delivery on the third screen. Location is displayed on a map to get the visual idea of the delivery address.

### Generate Google Maps API Key

In order to view the Google Maps, you need to provide Map API key in the manifest file. Please follow the procedure to generate API key for the app:

* Log on to https://cloud.google.com/console/google/maps-apis/overview
* Go to the Google Cloud Platform Console.
* Create or select a project.
* Select `Maps SDK for Android` from the list and click `Enable`.
* Under the Credentials tab, click in the `Credentials API Manager`.
* Click on the `Create Credentials` dropdown and select `API Key`.
* A new API key will be generated. Copy the API key
* Go to project's `AndroidManifest.xml` file and look for the following snippet:
```xml
    <meta-data
        android:name="com.google.android.geo.API_KEY"
        android:value="YOUR_API_KEY"/>
```
* Replace the `YOUR_API_KEY` with the API key that you generated

That's all!
