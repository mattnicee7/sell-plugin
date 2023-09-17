# Sell Plugin

## Overview

The Sell Plugin is a comprehensive solution for Minecraft servers that allows players to sell items directly from their inventory. It offers various selling methods, including automatic selling and shift-based selling. The plugin also integrates with the Vault plugin to handle in-game currency transactions.

## Features

- **Sell Command**: Players can use the `/sell` command to sell items from their inventory. The plugin checks for specific permissions and provides feedback via action bars.
- **Auto Sell**: Players can activate automatic selling with the `/autosell` command. Once activated, items are automatically sold from their inventory at a specified interval.
- **Shift Sell**: By using the `/shiftsell` command, players can activate or deactivate the shift-based selling feature. When activated, players can sell items by simply sneaking.
- **Bonus System**: Players can receive bonuses on their sales based on their permissions. For instance, a player with the permission `sellplugin.bonus.43` would receive a 43% bonus on their sales.
- **Customizable Messages**: Server administrators can customize feedback messages, such as the selling confirmation and bonus notifications.
- **Configurable Item Prices**: The plugin allows server administrators to set prices for specific items in the configuration.

## Installation

1. Ensure you have the Vault plugin installed, as this plugin requires it for economy integration.
2. Download and build the Sell Plugin following the build section.
3. Place the compiled `.jar` file into your server's `plugins` directory.
4. Restart your server.
5. Configure the plugin as needed by editing the generated configuration file.

## Build

To build the Sell Plugin from source, follow these steps:

1. Ensure you have Gradle installed on your machine.
2. Clone the repository to your local machine.
3. Navigate to the project directory.
4. Run the following command to build the plugin using Gradle and the ShadowJar plugin:
```gradle
gradle shadowJar
```
5. Upon successful build, the compiled `.jar` file with all its dependencies can be found in the `build/libs` directory.

## Recommended Versions

For optimal performance and compatibility, it's recommended to use the following versions:

- **Java**: Version 11
- **Minecraft Server**: 1.8.8
- **Gradle**: Latest stable release

Ensure that your server and development environment align with these recommendations to avoid potential issues.

## Support

For any issues, suggestions, or bugs, please open an issue/pull request.
