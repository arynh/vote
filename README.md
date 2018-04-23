# Vote‽

Voter information application

## Installation

Download the APK on an Android phone, or build it from the source.

## Usage/Experience

### Initial Screen

Hopefully there's a pretty splash screen idk.

### Location

* Allow the user to input a location (API needed for parsing).
* (stretch goal) Have a button which retrieves the user's current location.
* Reject locations which are too general (i.e. Illinois).

### Visual Layout

* [Four panes](https://developer.android.com/training/implementing-navigation/lateral.html) (the swipey android screen things): Local, state, national level political information, then polling locations.
* Perhaps maintain the location input at the top above the pane selector.
* Cards similar to something like the transit app, or with a shadow, hopefully just make it pretty.

### Information

The panes should go in order of local $\rightarrow$ national to emphasize the importance of local politics.

#### Local

* Voting locations, registration information
* Local politicians, judges, superintendent of schools, et cetera.
* Maybe pictures to make it pretty

#### State

* State legislators, governor, AG, et cetera.
* Maybe pictures again

#### National

* Congresspeople, important supreme court cases, congressional activities for the day, et cetera.

#### Polling Place

* Use Google Maps API to show location of polling place
* Have ```Get Directions``` button.

## Development

- [x] Continue ideation phase and transition into first prototype.
- [x] Finish initial UI.
- [ ] Design data flow.
- [ ] Connect APIs to data pipeline.
- [ ] Optimize and make it pretty.

### APIs

* [Google Civic Information API](https://developers.google.com/civic-information/)
    * Elections
    * Voter information based on location
* ~~Google Maps Geocoding API~~ (The Google Civic Information API automatically geocodes given addresses and return a 400 error if the input address is unparsable.)
* Other political APIs?
* [Google Maps Android API](https://developers.google.com/maps/documentation/android-api/)

    #### To use an API key:
    * Create ```.../vote/secrets.properties``` and in it place ```API_KEY=<the api key here>```.
    * In the java file, retrieve the key with ```BuildConfig.API_KEY```.
