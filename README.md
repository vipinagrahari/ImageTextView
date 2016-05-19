ImageTextView (1.0)
===================


ImageTextView is a beautiful android library which simplifies a very common task in android application development that is creating list of people with their image and name.

----------


Screenshot
-------------

We need to create list of people in most of the apps we develop. Everytime we have to make layouts and crop the images to show face of person and fit the view. **ImageTextView** does this for you automatically. 

Below is an example.

![ImageTextView Screenshot](https://raw.githubusercontent.com/vipinagrahari/ImageTextView/master/screenshots/screen1.png)


Features
-------------

 1. **Round Image View** for person image.
 2. **Title** TextView for person name. 
 3. **SubTitle** Text for person detail.
 4. **Face Detection** to automatically detect face position in image and crop automatically to fit the round Image View.
 

How To Use
-------------
#### Include Library in your project ####
just include the following line your **build.gradle (Module:app)** file and sync project.

    compile 'io.github.vipin.imagetextview:imagetextview:1.0'

After including the library you are all set to use it. Below is a code sample to use **ImageTextView**.

    <io.github.vipin.imagetextview.ImageTextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:imageSrc="@drawable/sherlock"
        app:imageSubTitle="Benedict Cumberbatch"
        app:imageTitle="Sherlock Holmes"
        android:id="@+id/v1"
        /> 

You have to specify 3 attribute values.

 1. Drawable Image Resource (Image must have a face otherwise app might crash)
 2. Title for image  e.g. Name of person
 3. Subtitle for Image e.g. Designation of person

Please Report any issue or suggestions you have.

License
-------------

[Apache 2.0](https://github.com/vipinagrahari/ImageTextView/edit/master/LICENSE.md)





 
