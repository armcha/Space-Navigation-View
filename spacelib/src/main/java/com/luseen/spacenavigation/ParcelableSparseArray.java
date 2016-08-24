/*
 * Space Navigation library for Android
 * Copyright (c) 2016 Arman Chatikyan (https://github.com/armcha/Space-Navigation-View).
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
package com.luseen.spacenavigation;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

/**
 * Created by Chatikyan on 24.08.2016-21:22.
 */

class ParcelableSparseArray extends SparseArray<Object> implements Parcelable {

    private SparseArray<Object> sparseArray;

    ParcelableSparseArray() {
        super();
    }

    public static final Parcelable.Creator<ParcelableSparseArray> CREATOR = new Creator<ParcelableSparseArray>() {

        @Override
        public ParcelableSparseArray createFromParcel(Parcel source) {
            return new ParcelableSparseArray(source);
        }

        @Override
        public ParcelableSparseArray[] newArray(int size) {
            return new ParcelableSparseArray[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeSparseArray(sparseArray);
    }

    @SuppressWarnings("unchecked")
    private ParcelableSparseArray(Parcel in) {
        sparseArray = in.readSparseArray(ParcelableSparseArray.class.getClassLoader());
    }
}
