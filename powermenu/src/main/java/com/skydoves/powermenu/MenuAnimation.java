
/*
 * Copyright (C) 2017 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.powermenu;

public enum MenuAnimation {
    SHOWUP_TOP_RIGHT(R.style.ShowUpAnimation_TR),//
    SHOWUP_TOP_LEFT(R.style.ShowUpAnimation_TL),//
    SHOWUP_BOTTOM_RIGHT(R.style.ShowUpAnimation_BR),//
    SHOWUP_BOTTOM_LEFT(R.style.ShowUpAnimation_BL),//
    SHOW_UP_CENTER(R.style.ShowUpAnimation_Center),//
    ELASTIC_TOP_RIGHT(R.style.ElasticMenuAnimation_TR),//
    ELASTIC_TOP_LEFT(R.style.ElasticMenuAnimation_TL),//
    ELASTIC_BOTTOM_RIGHT(R.style.ElasticMenuAnimation_BR),//
    ELASTIC_BOTTOM_LEFT(R.style.ElasticMenuAnimation_BL),//
    ELASTIC_CENTER(R.style.ElasticMenuAnimation_Center),//
    FADE(R.style.FadeMenuAnimation),//
    DROP_DOWN(-1),//
    NONE(0);

    int animationStyle;

    MenuAnimation(int animationStyle) {
        this.animationStyle = animationStyle;
    }
}