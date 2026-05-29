# Music_Mix

MelodyMix is a versatile and feature-rich music player app designed exclusively for Android devices. Developed using Java and the Android SDK, this app provides a delightful music listening experience, allowing users to import and play their favorite .mp3 files directly from their device's storage.

Key Features:
🎵 Seamless Music Playback: MelodyMix offers smooth and uninterrupted music playback, ensuring a pleasurable experience for users while enjoying their favorite tunes.

🎵 Import Your Music Library: The app leverages Java's file handling capabilities to effortlessly scan and access .mp3 files stored on the user's device. Users can quickly import their entire music collection and organize it with ease.

🎵 User-Friendly Interface: MelodyMix boasts an intuitive and visually appealing interface, making it simple for users to navigate through their music library, select songs, and control playback with intuitive gestures.

🎵 Continuous Play: Enjoy non-stop music with the app's continuous playback feature. Seamlessly transition from one song to another without any interruptions.

🎵 Audio Controls: Take full control of your music with playback controls like play, pause, skip, shuffle, and repeat, empowering users to curate their own personalized music experience.

🎵 Performance Optimization: Built for efficiency, MelodyMix ensures optimal app performance, responsiveness, and low resource consumption, providing a smooth user experience.

MelodyMix is the ultimate choice for music enthusiasts who seek a powerful and reliable music player for their Android devices. Whether you want to relax with soothing melodies or get energized with lively beats, MelodyMix has got you covered. Experience the joy of music with MelodyMix today! 🎶🎧

## Fastlane setup

Fastlane has been added for the Android project in `Music Mix/` with:

- `Gemfile`
- `fastlane/Fastfile`
- `fastlane/Appfile`
- GitHub Actions workflow: `.github/workflows/fastlane-android.yml`

### Available lanes

- `bundle exec fastlane android ci`  
  Runs `clean`, `test`, and `assembleDebug`.
- `bundle exec fastlane android release_build`  
  Runs `clean` and `assembleRelease`.

### GitHub Actions triggers

- Manual run via `workflow_dispatch`
- Automatic run on pushes to `main`

### Files/secrets needed from you

Current setup does not require signing files or Play Store credentials.

If you want signed release upload/deployment later, provide:

- Android keystore file (for example `release.keystore`)
- Keystore alias
- Keystore/store passwords
- Play Store service account JSON

Recommended GitHub secrets for future release automation:

- `ANDROID_KEYSTORE_BASE64`
- `ANDROID_KEYSTORE_PASSWORD`
- `ANDROID_KEY_ALIAS`
- `ANDROID_KEY_PASSWORD`
- `PLAY_STORE_JSON_KEY`
