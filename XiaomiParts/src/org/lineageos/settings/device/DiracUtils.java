/*
 * Copyright (C) 2019 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings.device;

final class DiracUtils {

    private DiracSound mDiracSound;

    DiracUtils() {
        mDiracSound = new DiracSound(0, 0);
    }

    void onBootCompleted() {
        setEnabled(mDiracSound.getMusic() == 1);
        mDiracSound.setHeadsetType(mDiracSound.getHeadsetType());
        setLevel(getLevel());
    }

    void setEnabled(boolean enable) {
        mDiracSound.setEnabled(enable);
        mDiracSound.setMusic(enable ? 1 : 0);
    }

    boolean isDiracEnabled() {
        return mDiracSound.getMusic() == 1;
    }

    void setLevel(String preset) {
        String[] level = preset.split("\\s*,\\s*");
        for (int band = 0; band <= level.length - 1; band++) {
            mDiracSound.setLevel(band, Float.valueOf(level[band]));
        }
    }

    private String getLevel() {
        String selected = "";
        for (int band = 0; band <= 6; band++) {
            int temp = (int) mDiracSound.getLevel(band);
            selected += String.valueOf(temp);
            if (band != 6) selected += ",";
        }
        return selected;
    }

    void setHeadsetType(int paramInt) {
        mDiracSound.setHeadsetType(paramInt);
    }
}
