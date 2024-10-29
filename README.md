# android_virtual_cam

A virtual camera based on Xposed

# I added some small changes to be able to randomly change to another video when the video finishes playing, inserting a random audio file 

# Do you want to change it to suit your application?

## CONNECT WITH ME : thucvan025@gmail.com

## DO NOT USE FOR ANY ILLEGAL PURPOSE, YOU NEED TO TAKE ALL RESPONSIBILITY AND CONSEQUENCE!

## Supported platform

- Android 5.0+

## Usage

1. Install this module , enable it in Xposed . Lsposed and other framework which have a scope list, you need to choose target app instead of System Framework.

2. In system Setting, authorize target to access local storage, and force stop the app. If the app does not request this permission, see step3.

3. open target app, if the app does not have the permission to access local storage. There will be a toast message showing that `Camera1` directory has been redirect to app's private directory `/[INTERNEL_STORAGE]/Android/data/[package_name]/files/Camera1/`. If there isn't the message, the default `Camera1` directory is `/[INTERNEL_STORAGE]/DCIM/Camera1/`. If the directory doesn't exist. Please create it by yourself.

> Attention: `Camera1` in the private directory only works for single app.

4. Open the camera in target app. There will be a toast message showing the resolution (width: , height:) . And you need to adjust the replacing video's resolution to make them same. put it under `Camera1` directory.

5. If there is a toast message when you take photos in app ("发现拍照")，it shows the photo's resolution. You need to prepare a photo which has the same resolution. Name it as `1000.bmp` . Put it under `Camera1` directory. (it support other image format renamed to bmp ). If there isn't a toast message , `1000.bmp` will have nothing to do with replacing capture.

6. If you need to play video's sound, create `./DCIM/Camera1/Camera1/{your list video}` under `Camera1` directory. (Global real-time effective)

7. If you need to turn off the module temporarily, create `./DCIM/Camera1/Camera1/{your list video}` under `Camera1` directory. (Global real-time effective)

8. If you find toast messages annoying, you can create a `no_toast.jpg` file in the `/[INTERNEL_STORAGE]/DCIM/Camera1/` directory. (Global real-time effective)

9. The directory redirection message is displayed only once by default. If you miss the toast message of directory redirection, you can create a `force_show.jpg` file in the `/[INTERNEL_STORAGE]/DCIM/Camera1/` directory to override the default setting. (Global real-time effective)

10. If you need to allocate videos for each application, you can create `private_dir.jpg` in the `/[INTERNEL_STORAGE]/DCIM/Camera1/` directory to enforce apps use private directory. (Global real-time effective)

> Note: the configuration of 6 ~ 10 are in the application. You can quickly configure them in the application or create files manually.

## Question report:

raise it in issues directly. If it is a bug, please attach with Xposed **modules** log.

## Credit

created from project Xposed-Modules-Repo : https://github.com/Xposed-Modules-Repo/com.example.vcam

Provide hook method: https://github.com/wangwei1237/CameraHook

H.264 hardware decode： https://github.com/zhantong/Android-VideoToImages

JPEG-YUV convert： https://blog.csdn.net/jacke121/article/details/73888732  
