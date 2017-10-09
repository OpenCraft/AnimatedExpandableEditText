# AnimatedExpandableEditText
A simple cool animated edit text with a expandable animation

![Animated Expandable Edit Text Animation](https://media.giphy.com/media/3ohhwxUxWnytY33gRi/giphy.gif)

## Installation:
1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
2. Add the dependency
```
dependencies {
  compile 'com.github.OpenCraft:AnimatedExpandableEditText:-SNAPSHOT'
}
```

## Usage:
Start using the expandable edit text adding the component below inside your layout file:
```
<com.gcherubini.animatedexpandableedittext.AnimatedExpandableEditText
  android:layout_width="match_parent"
  android:layout_height="wrap_content" />
```

**Customizing:** <br />

Create your own res/values/dimens.xml and res/values/integers.xml files in order to override default behavioural configurations:

```
<dimen name="animated_expandable_edit_text_expanded_height">35dp</dimen>
<integer name="animated_expanded_edit_text_animation_duration_milliseconds">400</integer>
```
