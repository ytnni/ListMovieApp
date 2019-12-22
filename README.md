# ListMovieApp
MVVM with clean architecture

The application has the following features:

- The main page has two tabs: Movies, Show.
- The Now Playing tab displays the movies.
- Each movie is shown as a poster picture in the grid, with rating stars and favorite state icon on top of it.
- You can pull down the list to refresh.
- Clicking on the movie poster will navigate to the movie details page.

This is view layer basically structure of whole android project (fragment/activity)

2. Model

View Model

ViewModel is one of the most important things in MVVM architecture. Unlike MVP architecture, it makes the loose coupling between activities and business logic. ViewModels does not have dependencies of UI components. So It makes easier unit testing. And ViewModel holds data about UI and it is lifecycle-aware.
It makes easier implementing landscape mode, and we do not need to release observers when the UI component is destroyed.
common : genre
detailmovie: detailmovie, productionCompany, productionCountry, Spoken language
detailshow : created by, detail tvShows, LastEpisodeToAir, Network, NextEpisodeToAir, ProductionCompany, seson
movies: Movie, Movies
tvshows: tvShow, TVshows

3. TheMovieDbAPI
 
I build movielist app that consumes a REST api using retrofit and displays images using Picasso and bring in Retrofit and 
show real movie posters as well as detail information for each movie.
i'm using The Movie Database Api to get some real data into our app

- LiveData : notify domain layer data to views.
- Lifecycle : dispose observing data when lifecycle state changes.
- ViewModel : UI related data holder, lifecycle aware.
- Room Persistence : construct database.


- Retrofit2 & Gson : constructing the REST API
- OkHttp3 : implementing interceptor, logging and mocking web server
- Glide :  loading images
- BaseRecyclerViewAdapter  : implementing adapters and viewHolders
- Ripple animation, Shared element transition

Now that injection of PostApi injection has been done, let's retrieve the data from the API. 
i will need to perform the call in background thread while i want to perform actions with the result on Android main thread. 
To do so, we will use RxAndroid library. 
In order to add dependency to that library in my project, just add the following lines to the build.gradle file of the module
 'rxjava'   : "io.reactivex.rxjava2:rxjava:${versions.rxjava}",
                    'rxAndroid': "io.reactivex.rxjava2:rxandroid:${versions.rxAndroid}",





What I learned?
- Fetch data from the Internet with theMovieDB API
- Use adapters and custom list layouts to populate list views
- Incorporate libraries to simplify the amount of code you need to write
- Build a fully featured application that looks and feels natural on the latest Android operating system.


 
