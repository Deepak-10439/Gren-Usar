package com.example.gren_usar

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

@Preview
@Composable
fun Checkout_Screen_2() {
    val context = LocalContext.current
    val cameraPermissionGranted = remember { mutableStateOf(false) }
    val galleryPermissionGranted = remember { mutableStateOf(false) }
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val photoTaken = remember { mutableStateOf<Bitmap?>(null) }
    val storageReference = FirebaseStorage.getInstance().reference
    var imageURL: String
    val launcherCameraPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        cameraPermissionGranted.value = isGranted
    }

    val launcherGalleryPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        galleryPermissionGranted.value = isGranted
    }

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            val uri = imageUri.value
            if (uri != null) {
                photoTaken.value = getBitmapFromUri(context, uri)
            }
        }
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            uri?.let {
                uploadImageToFirebase(context, it, storageReference) { downloadUrl ->
                    Log.d("ImageUpload", "Image URL: $downloadUrl")
                    // Launch the coroutine to make the API call
                    CoroutineScope(Dispatchers.Main).launch {
                        makeApiCall(downloadUrl)
                    }
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        val readExternalStoragePermission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (readExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            launcherGalleryPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        } else {
            galleryPermissionGranted.value = true
        }

        val cameraPermission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.CAMERA
        )
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            launcherCameraPermission.launch(Manifest.permission.CAMERA)
        } else {
            cameraPermissionGranted.value = true
        }
    }

    Scaffold(
        topBar = { TopApp_Checkout(text = "Take a Photo \n  of Garbage") }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFF6F9FF))
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp, top = 25.dp)
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    photoTaken.value?.let {
                        Image(bitmap = it.asImageBitmap(), contentDescription = null)
                    }
                    AsyncImage(
                            model = selectedImageUri,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.FillBounds,
                            onSuccess = {

                            }
                        )

                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(25.dp)
                ) {
                    Column {
                        Icon(
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = null,
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .clickable {
                                    if (cameraPermissionGranted.value) {
                                        val photoFile = createImageFile(context)
                                        val uri = FileProvider.getUriForFile(
                                            context,
                                            "${context.packageName}.fileprovider",
                                            photoFile
                                        )
                                        imageUri.value = uri
                                        takePictureLauncher.launch(uri)
                                    } else {
                                        Log.d("Checkout_Screen_2", "Camera permission not granted")
                                    }
                                }
                        )
                        Text(
                            text = "Open Camera",
                        )
                    }
                    Column {
                        Icon(
                            painter = painterResource(id = R.drawable.gallery3),
                            contentDescription = null,
                            modifier = Modifier
                                .width(70.dp)
                                .height(100.dp)
                                .clickable {
                                    singlePhotoPickerLauncher.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                }
                        )
                        Text(
                            text = "Open Gallery",
                        )
                    }
                }

                if (photoTaken.value != null) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .clickable {
                                    // Handle right tick action
                                    photoTaken.value?.let {
                                        uploadImageToFirebase(
                                            context,
                                            it,
                                            storageReference
                                        ) { downloadUrl ->
                                            Log.d("ImageUpload", "Image URL: $downloadUrl")
                                        }
                                    }
                                }
                        )

                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .clickable {
                                    // Handle wrong tick action
                                    photoTaken.value = null
                                }
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(bottom = 30.dp, top = 20.dp)
                        .shadow(
                            elevation = 38.dp,
                            spotColor = Color(0x123A4C82),
                            ambientColor = Color(0x123A4C82)
                        )
                        .fillMaxWidth()
                        .height(70.dp)
                        .background(color = Color(0xFFFFFFFF)),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(290.dp)
                            .height(48.dp)
                            .background(
                                color = Color(0xFF33907C),
                                shape = RoundedCornerShape(35.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Continue",
                            style = TextStyle(
                                fontSize = 18.sp,
                                lineHeight = 18.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center,
                            ),
                            modifier = Modifier.clickable {
                                // Save photo to gallery and continue
                                photoTaken.value?.let {
                                    saveImageToGallery(context, it)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

fun uploadImageToFirebase(context: Context, uri: Uri, storageReference: StorageReference, onComplete: (String) -> Unit) {
    val fileName = UUID.randomUUID().toString() + ".jpg"
    val ref = storageReference.child("images/$fileName")
    ref.putFile(uri)
        .addOnSuccessListener { taskSnapshot ->
            ref.downloadUrl.addOnSuccessListener { downloadUrl ->
                onComplete(downloadUrl.toString())
            }
        }
        .addOnFailureListener { e ->
            Log.e("Firebase", "Image upload failed", e)
        }
}
private var Apiresponse: String = ""
suspend fun makeApiCall(fileUrl: String) {
    val url = "https://api.edenai.run/v2/image/object_detection"
    val jsonPayload = """
        {
            "response_as_dict": true,
            "attributes_as_list": false,
            "show_base_64": true,
            "show_original_response": false,
            "file_url": "$fileUrl",
            "providers": ["clarifai"]
        }
    """.trimIndent()

    val client = OkHttpClient()

    val mediaType = "application/json".toMediaTypeOrNull()
    val requestBody = RequestBody.create(mediaType, jsonPayload)
    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .addHeader("accept", "application/json")
        .addHeader("content-type", "application/json")
        .addHeader("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNmQ1Nzc5MDEtNzM1Zi00NTIwLWJmNjgtMDYxMWMyMTUwNTI1IiwidHlwZSI6ImFwaV90b2tlbiJ9.wm-r2y1juK55zL8vTsf0ahD5yYpFiUbG5qdXU0VXA3s")
        .build()

    withContext(Dispatchers.IO) {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("API Error", "Failed to call API", e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val result = response.body?.string()
                    Log.d("API Response", result ?: "No response body")
                    Apiresponse = result ?: ""
                } else {
                    val errorBody = response.body?.string()
                    Log.e("API Error", "Failed to call API: ${response.code} - $errorBody")
                }
            }
        })
    }
}

private fun createImageFile(context: Context): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val storageDir = context.getExternalFilesDir(null)
    return File.createTempFile(
        "JPEG_${timeStamp}_",
        ".jpg",
        storageDir
    )
}

private fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val source = ImageDecoder.createSource(context.contentResolver, uri)
        ImageDecoder.decodeBitmap(source)
    } else {
        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    }
}

private fun saveImageToGallery(context: Context, bitmap: Bitmap) {
    MediaStore.Images.Media.insertImage(
        context.contentResolver,
        bitmap,
        "Image",
        "Image captured by camera"
    )
    // Optionally display a toast or notify the user
}
private fun uploadImageToFirebase(context: Context, bitmap: Bitmap, storageReference: StorageReference, onUrlReady: (String) -> Unit) {
    val imageName = UUID.randomUUID().toString()
    val imageRef = storageReference.child("images/$imageName")

    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val data = baos.toByteArray()

    val uploadTask = imageRef.putBytes(data)
    uploadTask.addOnSuccessListener { taskSnapshot ->
        imageRef.downloadUrl.addOnSuccessListener { uri ->
            onUrlReady(uri.toString())
        }.addOnFailureListener { exception ->
            Log.e("FirebaseUpload", "Failed to get download URL", exception)
        }
    }.addOnFailureListener { exception ->
        Log.e("FirebaseUpload", "Failed to upload image", exception)
    }
}
