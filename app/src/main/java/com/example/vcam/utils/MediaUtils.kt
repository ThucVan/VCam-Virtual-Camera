package com.example.vcam.utils

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import com.example.vcam.model.MediaModel
import java.io.File

internal const val LIMIT_PHOTO_LOAD_MORE = 300

class MediaUtils {
    fun getAllVideoFromPath(
        context: Context, path: String, offset: Int = 0
    ): ArrayList<MediaModel> {
        val listVideo = arrayListOf<MediaModel>()
        val contentUri: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        }
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DATE_MODIFIED,
        )
        val selection = MediaStore.Video.Media.DATA + " like ? "
        val sortOrder =
            MediaStore.Video.Media.DATE_MODIFIED + " DESC LIMIT $LIMIT_PHOTO_LOAD_MORE OFFSET ${offset * LIMIT_PHOTO_LOAD_MORE}"

        try {
            val cursor: Cursor? = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                val bundle = Bundle()
                bundle.putInt(ContentResolver.QUERY_ARG_LIMIT, LIMIT_PHOTO_LOAD_MORE)
                bundle.putInt(
                    ContentResolver.QUERY_ARG_OFFSET, offset * LIMIT_PHOTO_LOAD_MORE
                )
                bundle.putStringArray(
                    ContentResolver.QUERY_ARG_SORT_COLUMNS,
                    arrayOf(MediaStore.Video.Media.DATE_MODIFIED)
                )
                bundle.putInt(
                    ContentResolver.QUERY_ARG_SORT_DIRECTION,
                    ContentResolver.QUERY_SORT_DIRECTION_DESCENDING
                )
                // Selection
                bundle.putString(
                    ContentResolver.QUERY_ARG_SQL_SELECTION,
                    MediaStore.Video.Media.DATA + " like ? "
                )
                bundle.putStringArray(
                    ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS, arrayOf(
                        "%$path%"
                    )
                )
                context.contentResolver.query(contentUri, projection, bundle, null)
            } else {
                context.contentResolver.query(
                    contentUri, projection, selection, arrayOf("%$path%"), sortOrder
                )
            }
            // Cache column indices.
            cursor?.let {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
                val dataColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
                val displayNameColumn =
                    it.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
                val durationColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)

                while (it.moveToNext()) {
                    // Get values of columns for a given video.
                    val id = it.getLong(idColumn)
                    val data = it.getString(dataColumn)
                    val duration = it.getLong(durationColumn)
                    val displayName = it.getString(displayNameColumn)

                    val file = File(data)
                    if (file.exists() && duration > 0) {
                        val videoModel = MediaModel(
                            duration = duration, mediaName = displayName, mediaPath = data
                        )
                        listVideo.add(videoModel)
                    }
                }
                it.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return listVideo
    }

    fun getAllAudioFromPath(
        context: Context, path: String, offset: Int = 0
    ): ArrayList<MediaModel> {
        val listAudio = arrayListOf<MediaModel>()
        val contentUri: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        }
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATE_MODIFIED,
        )
        val selection = MediaStore.Audio.Media.DATA + " like ? "
        val sortOrder =
            MediaStore.Audio.Media.DATE_MODIFIED + " DESC LIMIT $LIMIT_PHOTO_LOAD_MORE OFFSET ${offset * LIMIT_PHOTO_LOAD_MORE}"

        try {
            val cursor: Cursor? = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                val bundle = Bundle()
                bundle.putInt(ContentResolver.QUERY_ARG_LIMIT, LIMIT_PHOTO_LOAD_MORE)
                bundle.putInt(
                    ContentResolver.QUERY_ARG_OFFSET, offset * LIMIT_PHOTO_LOAD_MORE
                )
                bundle.putStringArray(
                    ContentResolver.QUERY_ARG_SORT_COLUMNS,
                    arrayOf(MediaStore.Audio.Media.DATE_MODIFIED)
                )
                bundle.putInt(
                    ContentResolver.QUERY_ARG_SORT_DIRECTION,
                    ContentResolver.QUERY_SORT_DIRECTION_DESCENDING
                )
                // Selection
                bundle.putString(
                    ContentResolver.QUERY_ARG_SQL_SELECTION,
                    MediaStore.Audio.Media.DATA + " like ? "
                )
                bundle.putStringArray(
                    ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS, arrayOf(
                        "%$path%"
                    )
                )
                context.contentResolver.query(contentUri, projection, bundle, null)
            } else {
                context.contentResolver.query(
                    contentUri, projection, selection, arrayOf("%$path%"), sortOrder
                )
            }
            // Cache column indices.
            cursor?.let {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
                val displayNameColumn =
                    it.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
                val durationColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)

                while (it.moveToNext()) {
                    // Get values of columns for a given audio.
                    val id = it.getLong(idColumn)
                    val data = it.getString(dataColumn)
                    val duration = it.getLong(durationColumn)
                    val displayName = it.getString(displayNameColumn)

                    val file = File(data)
                    if (file.exists() && duration > 0) {
                        val audioModel = MediaModel(
                            duration = duration, mediaName = displayName, mediaPath = data
                        )
                        listAudio.add(audioModel)
                    }
                }
                it.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return listAudio
    }
}