/*
 * Copyright (C) 2017 The Android Open Source Project
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
package com.mjob.bigburger.repository.common

import androidx.annotation.NonNull

data class Resource<T>(val status: Int, val data: T?, val errorType: Int?) {
    fun isError() = status == STATUS_ERROR

    companion object {
        const val STATUS_LOADING = 0
        const val STATUS_SUCCESS = 1
        const val STATUS_ERROR = -1

        /**
         * Helper method to create fresh state resource
         */
        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(STATUS_SUCCESS, data, null)
        }

        /**
         * Helper method to create error state Resources. Error state might also have the current data, if any
         */
        fun <T> error(item: T? = null): Resource<T> {
            return Resource(STATUS_ERROR, item, null)
        }

        /**
         * Helper methos to create loading state Resources.
         */
        fun <T> loading( data: T? = null): Resource<T> {
            return Resource(STATUS_LOADING, data, null)
        }
    }
}