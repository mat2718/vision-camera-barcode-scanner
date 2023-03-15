package com.visioncamerabarcodescanner;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import com.mrousavy.camera.frameprocessor.FrameProcessorPlugin;
import java.util.Collections;
import java.util.List;

public class VisionCameraBarcodeScannerPluginPackage implements ReactPackage {
  @NonNull
  @org.jetbrains.annotations.NotNull
  @Override
  public List<NativeModule> createNativeModules(@NonNull @org.jetbrains.annotations.NotNull ReactApplicationContext reactContext) {
    FrameProcessorPlugin.register(new VisionCameraBarcodeScannerPlugin());
    return Collections.emptyList();
  }

  @NonNull
  @org.jetbrains.annotations.NotNull
  @Override
  public List<ViewManager> createViewManagers(@NonNull @org.jetbrains.annotations.NotNull ReactApplicationContext reactContext) {
    return Collections.emptyList();
  }
}