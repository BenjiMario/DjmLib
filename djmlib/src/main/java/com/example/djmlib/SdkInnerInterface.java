package com.example.djmlib;

/*
 *                       Copyright (c) Benjinn
 *
 *                            (C) Benjinn 2019
 * All rights are reserved. Reproduction in whole or in part is
 * prohibited without the written consent of the copyright owner.
 * Benjinn reserves the right to make changes without notice at any time.
 * Benjinn makes no warranty, expressed, implied or statutory, including but
 * not limited to any implied warranty of merchantability or fitness for any
 * particular purpose, or that the use will not infringe any third party patent,
 * copyright or trademark. Benjinn must not be liable for any loss or damage
 * arising from its use.
 */
interface SdkInnerInterface {
    void registerObserver(SdkPublicInterface sdkObserver);
    void removeObserver(SdkPublicInterface sdkObserver);
}
