package ru.me.a24camera

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class MainActivity : AppCompatActivity() {
    private val REQUEST_TAKE_PHOTO = 1
    private val REQUEST_TAKE_THUMBNAIL = 2
    private lateinit var miniImage: ImageView
    private lateinit var fullImage: ImageView
    private lateinit var outputFileUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        miniImage = findViewById(R.id.miniImage)
        fullImage = findViewById(R.id.fullImage)

        val buttonFullImage: Button = findViewById(R.id.button_full_image)
        val buttonBitmap: Button = findViewById(R.id.button_bitmap)

        buttonFullImage.setOnClickListener {
            try {
                saveFullImage()
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this, "Camera app not found", Toast.LENGTH_SHORT).show()
            }
        }

        buttonBitmap.setOnClickListener {
            try {
                getThumbnailPicture()
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this, "Camera app not found", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_TAKE_PHOTO -> {
                if (resultCode == Activity.RESULT_OK) {
                    fullImage.setImageDrawable(null)
                    fullImage.invalidate()
                    fullImage.setImageURI(outputFileUri)
                } else {
                    Toast.makeText(this, "Image capture cancelled", Toast.LENGTH_SHORT).show()
                }
            }
            REQUEST_TAKE_THUMBNAIL -> {
                if (resultCode == Activity.RESULT_OK) {
                    val thumbnailBitmap = data?.extras?.get("data") as? Bitmap
                    if (thumbnailBitmap != null) {
                        miniImage.setImageBitmap(thumbnailBitmap)
                    } else {
                        Toast.makeText(this, "Failed to capture thumbnail!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Thumbnail capture cancelled", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                Toast.makeText(this, "Unhandled request code: $requestCode", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getThumbnailPicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_TAKE_THUMBNAIL)
    }

    private fun saveFullImage() {
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (!storageDir?.exists()!!) {
            storageDir.mkdirs()
        }

        val file = File(storageDir, "test.jpg")
        outputFileUri = FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.provider",
            file
        )

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        startActivityForResult(intent, REQUEST_TAKE_PHOTO)
    }
}

