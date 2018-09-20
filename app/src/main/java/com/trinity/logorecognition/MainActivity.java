package com.trinity.logorecognition;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;
import layout.BottomSheetFragment;

public class MainActivity extends AppCompatActivity {

    private LayoutInflater layoutInflater;

    private LinearLayout linearLayoutForFavourites;
    private LinearLayout linearLayoutForRecents;

    private HashMap<String, List<Integer>> brands;

    private HashMap<String, Bitmap> favBrands, recentBrands;

    private SQLiteDatabase brandsDatabase;

    private static final int PICK_GALLERY_IMAGE = 1;

    private static final int REQUEST_TAKE_PHOTO = 2;

    private ImageView imageView;

    private String currentPhotoPath;

    private BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // imageView = (ImageView) findViewById(R.id.imageView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dispatchTakePictureIntent();

                View v = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);

                dialog = new BottomSheetDialog(MainActivity.this);
                dialog.setContentView(v);
                dialog.show();
            }
        });

        layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        linearLayoutForFavourites = (LinearLayout) findViewById(R.id.linearLayoutForFavourites);
        linearLayoutForRecents = (LinearLayout) findViewById(R.id.linearLayoutForRecents);

        brands = new HashMap<>();
        favBrands = new HashMap<>();
        recentBrands = new HashMap<>();

        brandsDatabase = openOrCreateDatabase("Brands", MODE_PRIVATE, null);

        try {
            createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            saveImageInDB();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        getImageFromDB();

        getBrandsFromDB();

        setUpFavouritesView();
        setUpRecentsView();
    }

    @Override
    protected void onStop() {
        // clear database
        brandsDatabase.execSQL("DROP TABLE IF EXISTS brands");

        super.onStop();
    }

    public void closeDialog(View v) {
        dialog.cancel();
    }

    public void onRecentItemClick(View v) {
        showBrandInfo(v, R.id.recent_item_name, R.id.recent_item_image);
    }

    public void onFavItemClick(View v) {
        showBrandInfo(v, R.id.fav_item_name, R.id.fav_item_image);
    }

    private void showBrandInfo(View v, int name, int image) {
        BrandInfoActivity.brandName = ((TextView) v.findViewById(name)).getText().toString();

        Drawable drawable = ((ImageView) v.findViewById(image)).getDrawable();
        BrandInfoActivity.brandImage = ((BitmapDrawable) drawable).getBitmap();

        Intent intent = new Intent(MainActivity.this, BrandInfoActivity.class);
        intent.putExtra("Brand", ((TextView) v.findViewById(name)).getText().toString());
        startActivity(intent);
    }

    public void pickImageFromGallery(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_GALLERY_IMAGE);
    }

    public void dispatchTakePictureIntent(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                System.out.println("Error with creating image file!");
//            }
//
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(this,
//                        "com.trinity.logorecognition.fileprovider",
//                        photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
           }
//        }
//    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = DateFormat.getDateTimeInstance().format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();

        return image;
    }
/*
    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        // bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }

    private void setPic(Intent data) {

        Uri uri = data.getData();

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            // Log.d(TAG, String.valueOf(bitmap));
            imageView.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    } */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        dialog.cancel();

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            //setPic();
            // Log.i("taking photo result", "OK");


            final Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] bitmapdata = bos.toByteArray();
            final ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);


            Socket photoSocket = null;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Socket photoSocket = new Socket("ec2-18-222-74-203.us-east-2.compute.amazonaws.com", 8080);
                        System.out.println("socket 1 opened");

                        //final DataInputStream dis = new DataInputStream(photoSocket.getInputStream());
                        final DataOutputStream dos = new DataOutputStream(photoSocket.getOutputStream());
                        System.out.println("dis created");

                        final int size = bs.available();


                        byte[] data = new byte[size];
                        try {
                            bs.read(data);

                            dos.writeInt(size);
                            dos.write(data);
                            dos.flush();

                            BufferedReader reader = new BufferedReader(new InputStreamReader(photoSocket.getInputStream()));

                            String brand = null;
                            brand = reader.readLine();
                            System.out.println("dis read");
                            System.out.println("Message: " + brand);
                            reader.close();
                            dos.close();
                            bs.close();
                            photoSocket.close();

                            Intent intent = new Intent(getBaseContext(), BrandInfoActivity.class);
                            intent.putExtra("Brand", brand);
                            startActivity(intent);


                        } catch (IOException ex) {
                            System.out.println("ERROR:" + ex.getMessage());
                        }
                    } catch (IOException ex) {
                        System.out.println("ERROR:" + ex.getMessage());
                    }
                }
            }).start();
        }

        if (requestCode == PICK_GALLERY_IMAGE && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {

            //setPic(data);
        }
    }

    private void createDatabase() throws IOException {
        // name, image, is_favourite, is_recent
        brands.put("Adidas", Arrays.asList(R.drawable.adidas, 1, 1));
        brands.put("Redmond", Arrays.asList(R.drawable.redmond, 1, 1));
        brands.put("H&M", Arrays.asList(R.drawable.h_and_m, 0, 1));
        brands.put("Азбука Вкуса", Arrays.asList(R.drawable.azbuka_vkusa, 1, 0));
        brands.put("Apple", Arrays.asList(R.drawable.apple, 1, 1));
        brands.put("Chanel", Arrays.asList(R.drawable.chanel, 1, 1));
        brands.put("Intel", Arrays.asList(R.drawable.intel, 1, 1));
        brands.put("Nike", Arrays.asList(R.drawable.nike, 1, 1));

        brandsDatabase.execSQL("CREATE TABLE IF NOT EXISTS brands " +
                "(name VARCHAR, image BLOB, favourite INT(1), recent INT(1))");

        // save all brands in database
        for (Map.Entry<String, List<Integer>> brand :
                brands.entrySet()) {
            saveBrandInDB(brand.getKey(), brand.getValue());
        }
    }

    private void saveBrandInDB(String name, List<Integer> data) throws IOException {
        Bitmap image = BitmapFactory.decodeResource(getResources(), data.get(0));
        byte[] byteImage = getBitmapAsByteArray(image);

//        brandsDatabase.execSQL("INSERT INTO brands (name, image, favourite, recent) " +
//        "VALUES (" + name + ", " + byteImage.toString() + ", " + data.get(1) + ", " + data.get(2) +")");

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("image", byteImage);
        values.put("favourite", data.get(1));
        values.put("recent", data.get(2));

        brandsDatabase.insert("brands", null, values);
    }

    private byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);

        return outputStream.toByteArray();
    }

    private void getBrandsFromDB() {

        Cursor c = brandsDatabase.rawQuery("SELECT * FROM brands", null);

        int nameInd = c.getColumnIndex("name");
        int imageInd = c.getColumnIndex("image");
        int isFavouriteInd = c.getColumnIndex("favourite");
        int isRecentInd = c.getColumnIndex("recent");

        c.moveToFirst();
        while (!c.isAfterLast()) {
            String name = c.getString(nameInd);
            byte[] image = c.getBlob(imageInd);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            int isFavourite = c.getInt(isFavouriteInd);
            int isRecent = c.getInt(isRecentInd);

            if (isFavourite == 1) {
                favBrands.put(name, bmp);
            }

            if (isRecent == 1) {
                recentBrands.put(name, bmp);
            }

            c.moveToNext();
        }

        if (!c.isClosed()) {
            c.close();
        }
    }

    private void setUpFavouritesView() {
        if (favBrands.size() == 0) {
            TextView msg = (TextView) findViewById(R.id.no_favourite);
            msg.setVisibility(View.VISIBLE);
            msg.setText("There is no your favourite brands");
            return;
        }

        int i = 0;
        for (Map.Entry<String, Bitmap> favBrand :
                favBrands.entrySet()) {

            View view = layoutInflater.inflate(R.layout.favourite_item, linearLayoutForFavourites, false);
            LinearLayout scrollItemLayout = (LinearLayout) view.findViewById(R.id.fav_item_layout);
            ImageView imageView = (ImageView) view.findViewById(R.id.fav_item_image);
            TextView itemName = (TextView) view.findViewById(R.id.fav_item_name);

            itemName.setText(favBrand.getKey());
            imageView.setImageBitmap(favBrand.getValue());

            if (i == favBrands.size() - 1) {
                scrollItemLayout.setBackgroundResource(android.R.color.transparent);
            }

            linearLayoutForFavourites.addView(view);

            i++;
        }
    }

    private void setUpRecentsView() {
        if (recentBrands.size() == 0) {
            TextView msg = (TextView) findViewById(R.id.no_recent);
            msg.setVisibility(View.VISIBLE);
            msg.setText("There is no recent requests");
            return;
        }

        int i = 0;
        for (Map.Entry<String, Bitmap> recentBrand :
                recentBrands.entrySet()) {

            View view = layoutInflater.inflate(R.layout.recent_item, linearLayoutForRecents, false);
            LinearLayout scrollItemLayout = (LinearLayout) view.findViewById(R.id.recent_item_layout);
            ImageView imageView = (ImageView) view.findViewById(R.id.recent_item_image);
            TextView itemName = (TextView) view.findViewById(R.id.recent_item_name);

            itemName.setText(recentBrand.getKey());
            imageView.setImageBitmap(recentBrand.getValue());

            if (i == recentBrands.size() - 1) {
                scrollItemLayout.setBackgroundResource(android.R.color.transparent);
            }

            linearLayoutForRecents.addView(view);

            i++;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        // Do not iconify the widget; expand it by default
        searchView.setIconifiedByDefault(false);

        searchView.setQueryRefinementEnabled(true);

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
