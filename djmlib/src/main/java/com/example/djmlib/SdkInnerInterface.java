package com.example.djmlib;


interface SdkInnerInterface {
    void registerObserver(SdkPublicInterface sdkObserver);
    void removeObserver(SdkPublicInterface sdkObserver);
}
