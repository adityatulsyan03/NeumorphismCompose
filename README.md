# Neumorphism Compose Library

A modern Android library for creating beautiful neumorphic UI components in Jetpack Compose. This library provides easy-to-use modifiers and utilities to achieve the popular neumorphism design trend with customizable shadows, shapes, and light sources.

## Features

- 🎨 **Two Shadow Styles**: Flat (elevated) and Pressed (inset) effects
- 🔄 **Multiple Light Sources**: LEFT_TOP, RIGHT_TOP, LEFT_BOTTOM, RIGHT_BOTTOM
- 🎯 **Shape Support**: Rounded corners and oval shapes
- 🌓 **Theme Aware**: Automatic light/dark theme shadow color adaptation
- ⚡ **Performance Optimized**: Efficient shadow rendering using native Canvas APIs
- 🔧 **Easy Integration**: Simple modifier-based API

## Installation

### Using JitPack

Add JitPack repository to your project's `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the dependency to your app's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.adityatulsyan03:neumorphism-compose:1.0.0")
}
```

### Local Module

If using as a local module, add to your `settings.gradle.kts`:

```kotlin
include(":Neumorphism")
```

And in your app's `build.gradle.kts`:

```kotlin
dependencies {
    implementation(project(":Neumorphism"))
}
```

## Quick Start

### Basic Usage

```kotlin
import com.adityatulsyan03.neumorphism.*
import com.adityatulsyan03.neumorphism.shape.*

@Composable
fun NeumorphicCard() {
    Card(
        modifier = Modifier
            .size(120.dp)
            .neu(
                lightShadowColor = Color.White,
                darkShadowColor = Color.Gray,
                shadowElevation = 6.dp,
                shape = Flat(RoundedCorner(12.dp)),
                lightSource = LightSource.LEFT_TOP
            )
    ) {
        // Your content here
    }
}
```

### Using NeuAttrs for Reusability

```kotlin
@Composable
fun defaultFlatNeuAttrs() = NeuAttrs(
    lightShadowColor = lightShadow(),
    darkShadowColor = darkShadow(),
    shadowElevation = 6.dp,
    lightSource = LightSource.LEFT_TOP,
    shape = Flat(RoundedCorner(12.dp))
)

@Composable
fun MyComponent() {
    Box(
        modifier = Modifier.neu(defaultFlatNeuAttrs())
    ) {
        // Content
    }
}
```

## Core Components

### 1. NeuAttrs

The main configuration class for neumorphic effects:

```kotlin
data class NeuAttrs(
    val lightShadowColor: Color,      // Light shadow color
    val darkShadowColor: Color,       // Dark shadow color  
    val shadowElevation: Dp,          // Shadow depth/elevation
    val shape: NeuShape,              // Shape type (Flat or Pressed)
    val lightSource: LightSource      // Light direction
)
```

### 2. Shapes

#### Flat (Elevated Effect)
Creates an elevated appearance with shadows behind the component:

```kotlin
Card(
    modifier = Modifier.neu(
        NeuAttrs(
            lightShadowColor = Color.White,
            darkShadowColor = Color.Gray,
            shadowElevation = 8.dp,
            shape = Flat(RoundedCorner(16.dp)),
            lightSource = LightSource.LEFT_TOP
        )
    )
) { /* Content */ }
```

#### Pressed (Inset Effect)
Creates a pressed/inset appearance with inner shadows:

```kotlin
TextField(
    value = text,
    onValueChange = { text = it },
    modifier = Modifier.neu(
        NeuAttrs(
            lightShadowColor = Color.White,
            darkShadowColor = Color.Gray,
            shadowElevation = 6.dp,
            shape = Pressed(RoundedCorner(12.dp)),
            lightSource = LightSource.LEFT_TOP
        )
    )
)
```

### 3. Corner Shapes

#### RoundedCorner
```kotlin
RoundedCorner(radius = 12.dp)
```

#### Oval
```kotlin
Oval // For circular/oval shapes
```

### 4. Light Sources

Control shadow direction with different light source positions:

```kotlin
enum class LightSource {
    LEFT_TOP,       // Light from top-left
    RIGHT_TOP,      // Light from top-right  
    LEFT_BOTTOM,    // Light from bottom-left
    RIGHT_BOTTOM    // Light from bottom-right
}
```

## Theme Integration

### Automatic Theme Colors

Create theme-aware shadow colors:

```kotlin
@Composable
fun lightShadow() = if (!isSystemInDarkTheme()) 
    Color(0xFFFFFFFF) else Color(0x66494949)

@Composable  
fun darkShadow() = if (!isSystemInDarkTheme()) 
    Color(0xFFA8B5C7) else Color(0x66000000)
```

### Default Configurations

Set up reusable default configurations:

```kotlin
val defaultWidgetPadding = 16.dp
val defaultElevation = 6.dp
val defaultCornerShape: CornerShape = RoundedCorner(12.dp)

@Composable
fun defaultPressedAttrs() = NeuAttrs(
    lightShadowColor = lightShadow(),
    darkShadowColor = darkShadow(), 
    shadowElevation = defaultElevation,
    lightSource = LightSource.LEFT_TOP,
    shape = Pressed(defaultCornerShape)
)

@Composable
fun defaultFlatAttrs() = NeuAttrs(
    lightShadowColor = lightShadow(),
    darkShadowColor = darkShadow(),
    shadowElevation = defaultElevation, 
    lightSource = LightSource.LEFT_TOP,
    shape = Flat(defaultCornerShape)
)
```

## Examples

### Input Field with Neumorphic Effect

```kotlin
@Composable
fun NeumorphicTextField() {
    var text by remember { mutableStateOf("") }
    
    Card(
        elevation = CardDefaults.cardElevation(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .neu(defaultPressedAttrs()),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            placeholder = { Text("Enter text...") }
        )
    }
}
```

### Neumorphic Button

```kotlin
@Composable
fun NeumorphicButton(
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .neu(defaultFlatAttrs())
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
```

### Circular Icon Button

```kotlin
@Composable
fun NeumorphicIconButton(
    @DrawableRes iconRes: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(56.dp)
            .neu(
                NeuAttrs(
                    lightShadowColor = lightShadow(),
                    darkShadowColor = darkShadow(),
                    shadowElevation = 8.dp,
                    lightSource = LightSource.LEFT_TOP,
                    shape = Flat(Oval)
                )
            )
            .clickable { onClick() },
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
```

## Best Practices

### 1. Consistent Elevation
Use consistent elevation values across your app:
```kotlin
val smallElevation = 4.dp
val mediumElevation = 6.dp  
val largeElevation = 8.dp
```

### 2. Theme-Aware Colors
Always use theme-aware shadow colors for proper dark/light mode support.

### 3. Performance
- Avoid excessive nesting of neumorphic effects
- Use reasonable elevation values (4-12dp typically)
- Cache NeuAttrs objects when possible

### 4. Accessibility
Ensure sufficient color contrast when using neumorphic effects, especially in dark themes.

## Requirements

- **Minimum SDK**: 24 (Android 7.0)
- **Compile SDK**: 36
- **Jetpack Compose**: BOM 2024.x.x or later
- **Kotlin**: 1.9.0 or later

## License

```
Copyright 2024 Aditya Tulsyan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Author

**Aditya Tulsyan** - [GitHub](https://github.com/adityatulsyan03)

---

⭐ If you found this library helpful, please give it a star!
