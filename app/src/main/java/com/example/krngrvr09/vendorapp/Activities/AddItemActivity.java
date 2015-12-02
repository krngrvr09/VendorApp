package com.example.krngrvr09.vendorapp.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.krngrvr09.vendorapp.Models.Item;
import com.example.krngrvr09.vendorapp.R;
import com.example.krngrvr09.vendorapp.api.APIClient;
import com.example.krngrvr09.vendorapp.api.protocol.newItemResponse;
import com.github.clans.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class AddItemActivity extends AppCompatActivity {
    private static final String TAG = "AddItemActivity";
    /*---------------------------------------------------*/
    private String selectedImagePath = "";
    final private int PICK_IMAGE = 1;
    final private int CAPTURE_IMAGE = 2;
    String imgPath;
    public Uri imagePath;
    private static String[] PERMISSIONS_CAMERA = {Manifest.permission.CAMERA};
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_CAMERA = 3;
    private static final int REQUEST_STORAGE = 4;
    private View mLayout;


    public Uri setImageUri() {
        // Store image in dcim
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/", "image" + new Date().getTime() + ".png");
        Uri imgUri = Uri.fromFile(file);
        this.imgPath = file.getAbsolutePath();
        imagePath = imgUri;
        return imgUri;
    }


    public String getImagePath() {
        return imgPath;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_CANCELED) {
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri uri = data.getData();

                try {
                    imagePath = uri;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    Log.d("image", String.valueOf(bitmap) + "   " + imagePath);
                    int nh = (int) (image.getHeight() * (512.0 / image.getWidth()));
                    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                    image.setImageBitmap(scaled);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == CAPTURE_IMAGE) {
                selectedImagePath = getImagePath();
                image.setImageBitmap(decodeFile(selectedImagePath));

            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }


    public Bitmap decodeFile(String path) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, o);
            // The new size we want to scale to
            final int REQUIRED_SIZE = 70;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeFile(path, o2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;

    }


    /*-------------------------------------------*/
    ImageView image;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    FloatingActionButton take_photo;
    FloatingActionButton select_from_galary;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ActivityCompat.checkSelfPermission(AddItemActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermissions();
        } else if (ActivityCompat.checkSelfPermission(AddItemActivity.this, Manifest.permission_group.STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestStoragePermissions();
        }
        setContentView(R.layout.activity_add_item_3);
        mLayout = findViewById(R.id.add_item_view);
        final EditText add_name = (EditText) findViewById(R.id.new_name);
        final EditText add_price = (EditText) findViewById(R.id.new_price);
        final EditText add_quantity = (EditText) findViewById(R.id.new_quantity);
        take_photo = (FloatingActionButton) findViewById(R.id.take_from_camera);
        select_from_galary = (FloatingActionButton) findViewById(R.id.take_from_gallery);
        image = (ImageView) findViewById(R.id.image);
        Picasso.with(getApplicationContext()).load(R.drawable.default_image).fit().centerCrop().into(image);
        Button add_item = (Button) findViewById(R.id.add_button);
        select_from_galary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, ""), PICK_IMAGE);

            }
        });

        take_photo.setOnClickListener(new View.OnClickListener() {

                                          @Override
                                          public void onClick(View v) {
                                              Log.d(TAG, "else");
                                              Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                              intent.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
                                              startActivityForResult(intent, CAPTURE_IMAGE);

                                          }
                                      }

        );

        add_item.setOnClickListener(new View.OnClickListener()

                                    {
                                        @Override
                                        public void onClick(View view) {

                                            String name = String.valueOf(add_name.getText());
                                            String price = String.valueOf(add_price.getText());
                                            String quantity = String.valueOf(add_quantity.getText());

                                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                Bitmap bm;
                                                try {
                                                    bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                                                    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                                                    byte[] b = baos.toByteArray();
                                                    String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                                                    Item item = new Item(name, 0, "fb", Integer.parseInt(price), Integer.parseInt(quantity), "abc", 0);
                                                    APIClient apiClient = new APIClient();
                                                    apiClient.getmApi().createItem(item, new Callback<newItemResponse>() {
                                                        @Override
                                                        public void success(newItemResponse s, Response response) {
                                                            Log.d("new item", "success");

                                                        }

                                                        @Override
                                                        public void failure(RetrofitError error) {
//                                                            Log.d("new item", error.getCause().toString());

                                                        }
                                                    });
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }

                                        }
                                    }

        );

    }

    private void requestStoragePermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            Snackbar.make(mLayout, R.string.permission_storage_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat
                                    .requestPermissions(AddItemActivity.this, PERMISSIONS_STORAGE,
                                            REQUEST_STORAGE);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_CAMERA);
        }
    }

    private void requestCameraPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            Log.i(TAG,
                    "Displaying contacts permission rationale to provide additional context.");

            Snackbar.make(mLayout, R.string.permission_camera_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat
                                    .requestPermissions(AddItemActivity.this, PERMISSIONS_CAMERA,
                                            REQUEST_CAMERA);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS_CAMERA, REQUEST_CAMERA);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
