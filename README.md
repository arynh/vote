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

* [Four panes](https://developer.android.com/training/implementing-navigation/lateral.html) (the swipey android screen things): Local, state, national level political information, then a pane for the next election
* Perhaps maintain the location input at the top above the pane selector.
* Cards similar to something like the transit app, or with a shadow.

### Information

The panes should go in order of local $\rightarrow$ national to emphasize the importance local politics.

#### Local

* Voting locations, registration information
* Local politicians, judges, superintendent of schools, et cetera.
* Maybe pictures to make it pretty

#### State

* State legislators, governor, AG, et cetera.
* Maybe pictures again

#### National

* Congresspeople, important supreme court cases, congressional activities for the day, et cetera.

## Development

Continue ideation phase and transition into first prototype.

### APIs
* [Google Civic Information API](https://developers.google.com/civic-information/)
    * Elections
    * Voter information based on location
* [Google Maps Geocoding API](https://developers.google.com/maps/documentation/geocoding/start)
* Other political APIs
