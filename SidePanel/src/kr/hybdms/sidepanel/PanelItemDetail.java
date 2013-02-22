/*
 * SidePanel Application For Android
 * Copyright 2013 Young Bin Han
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

package kr.hybdms.sidepanel;

import android.graphics.drawable.Drawable;

public class PanelItemDetail {
    private Drawable imageId;
    public PanelItemDetail(Drawable images) {
        this.imageId = images;
    }
    public Drawable getImageId() {
        return imageId;
    }
    public void setImageId(Drawable imageId) {
        this.imageId = imageId;
    }
}