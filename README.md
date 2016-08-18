# Space-Navigation-View

##Introduction
------------
Space Navigation is a library allowing easily integrate fully customizable Google [Spaces][1] like navigation to your app.
[1]: https://play.google.com/store/apps/details?id=com.google.android.apps.social.spaces

![](screens/mainGif.gif)
![](screens/screen4.png)


The current minSDK version is API level 16 Android 4.1 (JELLY BEAN).

Download simple [apk][7]
[7]: https://github.com/armcha/Space-Navigation-View/raw/master/SpaceNavigationView.apk

#Youtube demos 

[Demo 1][2]
[2]: https://www.youtube.com/watch?v=LY-7abfJV2o
[Demo 2][3]
[3]: https://www.youtube.com/watch?v=rA1NMMLJ4TE

##Download magic
-----------------------


Gradle:
```groovy
repositories {
    maven {
        url 'https://dl.bintray.com/armcha/maven'
    }
}

compile 'com.github.armcha:SpaceNavigationView:1.0.0'
```
Maven:
```xml
<dependency>
  <groupId>com.github.armcha</groupId>
  <artifactId>SpaceNavigationView</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

## Setup and usage
------------------

Add the Space Navigation view to your layout

```xml
 <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
     xmlns:tools="http://schemas.android.com/tools"
     xmlns:app="http://schemas.android.com/apk/res-auto"
     android:layout_width="match_parent"
     android:layout_height="match_parent">
     
      <...View
             ....
             android:layout_marginBottom="@dimen/view_bottom_margin" />
             
      <com.luseen.spacenavigation.SpaceNavigationView
             android:id="@+id/space"
             android:layout_width="match_parent"
             android:layout_height="match_parent"
             android:layout_gravity="bottom"/>
             
 </FrameLayout>
```

Add Space Navigation items.

```java
   SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
   spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
   spaceNavigationView.addSpaceItem(new SpaceItem("HOME", R.drawable.yourDrawable));
   spaceNavigationView.addSpaceItem(new SpaceItem("SEARCH", R.drawable.yourDrawable));
```
Use ```initWithSaveInstanceState(savedInstanceState)``` and override ```onSaveInstanceState``` 
if you want to keep selected item position and badge on device rotation
```java
       @Override
       protected void onSaveInstanceState(Bundle outState) {
           super.onSaveInstanceState(outState);
           spaceNavigationView.onSaveInstanceState(outState);
       }
```

Set onClick listener
```java
   spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
               Toast.makeText(MainActivity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
               Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });
```

Customize
---------

Customize with xml

```xml
 <com.luseen.spacenavigation.SpaceNavigationView
        android:id="@+id/space"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="bottom"
        app:active_item_color="@color/colorAccent"
        app:centre_button_color="@color/centre_button_color"
        app:inactive_item_color="@color/white"
        app:space_background_color="@color/colorPrimary"
        app:space_item_icon_size="@dimen/space_item_icon_default_size"
        app:space_item_icon_only_size="@dimen/space_item_icon_only_size"
        app:space_item_text_size="@dimen/space_item_text_default_size" />
```

|  Attribute | Description |
|---|---|
| active_item_color  | item color when selected |
| inactive_item_color |  item color when unselected |
| centre_button_color | centre circle button color |
| space_background_color | space view background color |
| space_item_icon_size | item icon size |
| space_item_icon_only_size | item icon size on ```showIconOnly()``` mode |
| space_item_text_size | item text size |

Change space navigation background
```java
 spaceNavigationView.setSpaceBackgroundColor(ContextCompat.getColor(this, R.color.yourColor));
```

Change centre button icon 
```java
 spaceNavigationView.setCentreButtonIcon(R.drawable.yourDrawable);
```

Change centre button background color 
```java
 spaceNavigationView.setCentreButtonColor(ContextCompat.getColor(this, R.color.yourColor));
```

Change selected item text and icon color
```java
 spaceNavigationView.setActiveSpaceItemColor(ContextCompat.getColor(this, R.color.yourColor));
```

Change unselected item text and icon color
```java
  spaceNavigationView.setInActiveSpaceItemColor(ContextCompat.getColor(this, R.color.yourColor));
```

Change space item icon size
```java
  spaceNavigationView.setSpaceItemIconSize((int) getResources().getDimension(R.dimen.yourDimen));
```

Change space item icon size when ```showIconOnly();``` mode activated
```java
  spaceNavigationView.setSpaceItemIconSizeInOnlyIconMode((int) getResources().getDimension(R.dimen.yourDimen));
```

Change space item text size
```java
  spaceNavigationView.setSpaceItemTextSize((int) getResources().getDimension(R.dimen.yourDimen));
```

Hide items text and show only icons
```java
  spaceNavigationView.showIconOnly();
```
![](screens/screen2.png)

Hide items icon and show only texts
```java
  spaceNavigationView.showTextOnly();
```
![](screens/screen5.png)

You can change selected item programmatically
```java
  spaceNavigationView.changeCurrentItem(int tabIndexToSelect);
```

Show badge
```java
  spaceNavigationView.showBadgeAtIndex(int itemIndexToShowBadge, int badgeCountText, int badgeBackgroundColor);
```
![](screens/gif1.gif)

Hide badge at index
```java
  spaceNavigationView.hideBudgeAtIndex(int itemIndexToHideBudge);
```
![](screens/gif2.gif)

Hide all badges
```java
  spaceNavigationView.hideAllBudges();
```

Change badge text
```java
  spaceNavigationView.changeBadgeTextAtIndex(int itemIndexToChangeBadge, int badgeCountText);
```

Set your custom font
```java
   spaceNavigationView.setFont(Typeface.createFromAsset(getActivity().getAssets(), "your_cutom_font.ttf"));
```


##Versions

1.0.0 - Initial release

##Apps using the Space Navigation View
Kindly please let me know if you used or planning to use the library in your projects

## Contact 

Pull requests are more than welcome.
Please fell free to contact me if there is any problem when using the library.

- **Email**: armcha01@gmail.com
- **Facebook**: https://web.facebook.com/chatikyana
- **Google +**: https://plus.google.com/112011638040018774140
- **Website**: http://luseen.com/

License
--------


      Space Navigation library for Android
      Copyright (c) 2016 Arman Chatikyan (https://github.com/armcha/Space-Navigation-View).
      
      Licensed under the Apache License, Version 2.0 (the "License");
      you may not use this file except in compliance with the License.
      You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

      Unless required by applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License.
    
