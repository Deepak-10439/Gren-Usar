package com.example.gren_usar

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import com.example.gren_usar.data.EdenItem
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File
import java.util.UUID


@Composable
fun CheckoutScreen2(
    navController: NavController
) {
    val context = LocalContext.current
    val cameraPermissionGranted = remember { mutableStateOf(false) }
    val galleryPermissionGranted = remember { mutableStateOf(false) }
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val photoTaken = remember { mutableStateOf<Bitmap?>(null) }
    val storageReference = FirebaseStorage.getInstance().reference
    var apiResponse by remember { mutableStateOf<List<EdenItem>?>(null) }
    val selectedBitmap = remember { mutableStateOf<Bitmap?>(null) }

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
            uri?.let { imageUri ->
                val bitmap = getBitmapFromUri(context, imageUri)
                selectedBitmap.value = bitmap
                Toast.makeText(navController.context, "Analyzing Image, please wait...", Toast.LENGTH_LONG).show()// Upload image and get download URL
                uploadImageToFirebase(context, imageUri, storageReference) { downloadUrl ->
                    Log.d("ImageUpload", "Image URL: $downloadUrl")

                    CoroutineScope(Dispatchers.Main).launch {
                        val edenItems = makeApiCall(downloadUrl)

                        // Process and draw rectangles on the bitmap
                        edenItems?.let { items ->
                            val updatedBitmap = bitmap?.let { drawRectanglesOnBitmap(it, items) }
                            selectedBitmap.value = updatedBitmap
                            apiResponse = items
                        }
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
        topBar = { TopApp_Checkout_2() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFF6F9FF))
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp, top = 25.dp)
                        .fillMaxWidth()
                        .height(300.dp)
                        .clipToBounds(),
                    contentAlignment = Alignment.Center
                ) {
                    selectedBitmap.value?.let { bitmap ->
                        DrawRectanglesOnImage(apiResponse ?: emptyList(), bitmap)
                    }
                }
            }
            item {
                apiResponse?.let { response ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp)),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(response.groupBy { it.label }.map { (label, items) -> "$label * ${items.size}" }) { item ->
                            Text(
                                text = item,
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF479838),

                                    textAlign = TextAlign.Center,
                                )
                            )
                        }
                    }
                }
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(25.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
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
                                        Log.d("CheckoutScreen2", "Camera permission not granted")
                                    }
                                }
                        )
                        Text(text = "Open Camera", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.gallery3),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clickable {
                                    singlePhotoPickerLauncher.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                }
                        )
                        Text(text = "Open Gallery", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
                    }
                }
            }

            item {
                if (photoTaken.value != null) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .clickable {
                                    // Handle right tick action
                                    photoTaken.value?.let {
                                        // Upload image to Firebase (uncomment when ready)
                                        // uploadImageToFirebase(context, it, storageReference) { downloadUrl ->
                                        //     Log.d("ImageUpload", "Image URL: $downloadUrl")
                                        // }
                                    }
                                }
                        )

                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .clickable {
                                    // Handle wrong tick action
                                    photoTaken.value = null
                                }
                        )
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .shadow(
                            elevation = 38.dp,
                            spotColor = Color(0x123A4C82),
                            ambientColor = Color(0x123A4C82)
                        )
                        .fillMaxWidth()
                        .height(70.dp)
                        .background(color = Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(290.dp)
                            .height(48.dp)
                            .background(Color(0xFF33907C), RoundedCornerShape(15.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Proceed to Payment",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.clickable {
                                navController.navigate(GrenScreen.Check_out_Screen_3.name)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DrawRectanglesOnImage(edenItems: List<EdenItem>, bitmap: Bitmap?) {
    val imageBitmap = bitmap?.asImageBitmap()

    if (imageBitmap != null) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            // Draw the image
            drawImage(imageBitmap)
        }
    }
}
fun drawRectanglesOnBitmap(bitmap: Bitmap, items: List<EdenItem>): Bitmap {
    val mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true) // Ensure it's mutable
    val canvas = Canvas(mutableBitmap)
    val colors = listOf(
        android.graphics.Color.argb(255, 255, 0, 0),      // Red
        android.graphics.Color.argb(255, 0, 255, 0),      // Green
        android.graphics.Color.argb(255, 0, 0, 255),      // Blue
        android.graphics.Color.argb(255, 255, 255, 0),    // Yellow
        android.graphics.Color.argb(255, 0, 255, 255),    // Cyan
        android.graphics.Color.argb(255, 255, 0, 255),    // Magenta
        android.graphics.Color.argb(255, 255, 165, 0),    // Orange
        android.graphics.Color.argb(255, 128, 0, 128),    // Purple
        android.graphics.Color.argb(255, 75, 0, 130),     // Indigo
        android.graphics.Color.argb(255, 255, 192, 203)   // Pink
    )

    val width = mutableBitmap.width
    val height = mutableBitmap.height

    items.forEachIndexed { index, item ->
        val paint = Paint().apply {
            color = colors[index % colors.size]
            style = Paint.Style.STROKE
            strokeWidth = 5f
        }

        val left = (item.x_min.coerceIn(0f, 1f) * width)
        val top = (item.y_min.coerceIn(0f, 1f) * height)
        val right = (item.x_max.coerceIn(0f, 1f) * width)
        val bottom = (item.y_max.coerceIn(0f, 1f) * height)

        Log.d("drawRectanglesOnBitmap", "Rectangle coordinates: left=$left, top=$top, right=$right, bottom=$bottom")

        // Only draw the rectangle if it is within the bounds
        if (left < right && top < bottom) {
            canvas.drawRect(left, top, right, bottom, paint)
        }
    }

    return mutableBitmap
}

fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
    return try {
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            BitmapFactory.decodeStream(inputStream)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun createImageFile(context: Context): File {
    val storageDir = context.getExternalFilesDir(null)
    return File.createTempFile(
        "JPEG_${System.currentTimeMillis()}_",
        ".jpg",
        storageDir
    )
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
            e.printStackTrace()
            Toast.makeText(context, "Image upload failed", Toast.LENGTH_SHORT).show()
        }
}

suspend fun makeApiCall(fileUrl: String): List<EdenItem>? {
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

    return withContext(Dispatchers.IO) {
        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val result = response.body?.string()
                Log.d("API Response", result ?: "No response body")
                val jsonObject = JSONObject(result ?: "{}")
                val edenItemsArray = jsonObject.getJSONObject("eden-ai").getJSONArray("items")
                val edenItems = mutableListOf<EdenItem>()
                for (i in 0 until edenItemsArray.length()) {
                    val item = edenItemsArray.getJSONObject(i)
                    edenItems.add(
                        EdenItem(
                            label = item.getString("label"),
                            confidence = item.getDouble("confidence"),
                            x_min = item.getDouble("x_min").toFloat(),
                            x_max = item.getDouble("x_max").toFloat(),
                            y_min = item.getDouble("y_min").toFloat(),
                            y_max = item.getDouble("y_max").toFloat()
                        )
                    )
                }
                edenItems
            } else {
                Log.e("API Error", "Failed to call API: ${response.code} - ${response.body?.string()}")
                null
            }
        } catch (e: Exception) {
            Log.e("API Error", "Exception in API call", e)
            null
        }
    }
}