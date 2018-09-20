package com.trinity.logorecognition;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class BrandInfoActivity extends AppCompatActivity {

    public static String brandName;

    public static Bitmap brandImage;

    private ImageView imageView;

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_brand_info, menu);

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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_info_activity);

        String brand = getIntent().getStringExtra("Brand").toLowerCase();
        setTitle(brand.toUpperCase());

        switch (brand) {
            case "apple":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.apple);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.apple.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.applestore.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Electronics and gadgets");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("Apple Inc. is an American multinational technology company headquartered in Cupertino, California that designs, develops, and sells consumer electronics, computer software, and online services. The company's hardware products include the iPhone smartphone, the iPad tablet computer, the Mac personal computer, the iPod portable media player, the Apple Watch smartwatch, the Apple TV digital media player, and the HomePod smart speaker. Apple's software includes the macOS and iOS operating systems, the iTunes media player, the Safari web browser, and the iLife and iWork creativity and productivity suites, as well as professional applications like Final Cut Pro, Logic Pro, and Xcode. Its online services include the iTunes Store, the iOS App Store and Mac App Store, Apple Music, and iCloud.\n" +
                        "\n");
                break;
            }
            case "hp":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.hp);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.hp.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.hpstore.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Computers and printers");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("HP Inc. (also known simply as HP and stylized as hp) is an American technology company.\n" +
                        "\n" +
                        "It was formed on November 1, 2015, renamed from the personal computer and printer divisions of the original Hewlett-Packard Company, with its enterprise products and services businesses becoming Hewlett Packard Enterprise. The split was structured so that Hewlett-Packard changed its name to HP Inc. and spun off Hewlett Packard Enterprise as a new publicly traded company. HP Inc. retains Hewlett-Packard's pre-2015 stock price history and its former stock ticker symbol, HPQ, while Hewlett Packard Enterprise trades under its own symbol");

                break;
            }
            case "nvidia":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.nvidia);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.nvidia.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.nvidiastore.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Graphic hardware");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("Nvidia Corporation most commonly referred to as Nvidia, stylized as NVIDIA, nVIDIA, or nVidia) is an American technology company incorporated in Delaware and based in Santa Clara, California.[4] It designs graphics processing units (GPUs) for the gaming, cryptocurrency, and professional markets, as well as system on a chip units (SoCs) for the mobile computing and automotive market. Its primary GPU product line, labeled \"GeForce\", is in direct competition with Advanced Micro Devices' (AMD) \"Radeon\" products. Nvidia expanded its presence in the gaming industry with its handheld Shield Portable, Shield Tablet and Shield Android TV.");
                break;
            }
            case "bmw":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.bmw);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.bmw.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.bmwstore.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Cars and motorcycles");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("BMW (Bayerische Motoren Werke in German, or Bavarian Motor Works in English) is a German multinational company which currently produces automobiles and motorcycles, and also produced aircraft engines until 1945.\n" +
                        "\n" +
                        "The company was founded in 1916 and has its headquarters in Munich, Bavaria. BMW produces motor vehicles in Germany, Brazil, China, India, South Africa and the United States. In 2015, BMW was the world's twelfth largest producer of motor vehicles, with 2,279,503 vehicles produced.[2] The Quandt family are long-term shareholders of the company, with the remaining stocks owned by public float.\n" +
                        "\n" +
                        "Automobiles are marketed under the brands BMW (with sub-brands BMW M for performance models and BMW i for plug-in electric cars), Mini and Rolls-Royce. Motorcycles are marketed under the brand BMW Motorrad.");
                break;
            }
            case "google":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.google);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);


                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.google.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.googlestore.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Searching and Internet");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("Google LLC[5] is an American multinational technology company that specializes in Internet-related services and products, which include online advertising technologies, search engine, cloud computing, software, and hardware. Google was founded in 1998 by Larry Page and Sergey Brin while they were Ph.D. students at Stanford University, California. Together, they own about 14 percent of its shares and control 56 percent of the stockholder voting power through supervoting stock.\n.");
                break;
            }

            case "ferrari":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.ferrari);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);


                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.ferrari.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.ferraristore.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Cars and motorcycles");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("Ferrari N.V. is an Italian sports car manufacturer based in Maranello. Founded by Enzo Ferrari in 1939 out of Alfa Romeo's race division as Auto Avio Costruzioni, the company built its first car in 1940. However, the company's inception as an auto manufacturer is usually recognized in 1947, when the first Ferrari-badged car was completed.");
                break;
            }

            case "milka":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.milka);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.milka.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.milkastore.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Food and chocolate");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("Milka is a brand of chocolate confection which originated in Switzerland in 1901[1] and has been manufactured internationally by the US confectionery company Mondelēz International (formerly known as Kraft Foods) since 1990.[2][3] For more than 100 years, Milka has been primarily produced in Lörrach, Germany, producing about 140,000 tonnes of chocolate in 2012.[4] It is sold in bars and a number of novelty shapes for Easter and Christmas.[5] Milka also produces chocolate-covered cookies and biscuits.");
                break;
            }
            case "starbucks":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.starbucks);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.starbucks.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.starbucksstore.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Coffe and cafe");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("Starbucks Corporation is an American coffee company and coffeehouse chain. Starbucks was founded in Seattle, Washington in 1971. As of 2017, the company operates 27,339 locations worldwide.\n" +
                        "\n" +
                        "Starbucks is considered the main representative of \"second wave coffee\", initially distinguishing itself from other coffee-serving venues in the US by taste, quality, and customer experience while popularizing darkly roasted coffee.[5] Since the 2000s, third wave coffee makers have targeted quality-minded coffee drinkers with hand-made coffee based on lighter roasts, while Starbucks nowadays uses automated espresso machines for efficiency and safety reasons.");
                break;
            }

            case "ups":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.ups);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.ups.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.ups-store.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Post and delivery");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("United Parcel Service (UPS) is an American multinational package delivery company and a provider of supply chain management solutions.[4] The global logistics company is headquartered in the U.S. city of Sandy Springs, Georgia, which is a part of the Greater Atlanta metropolitan area.\n");
                break;

            }

            case "rittersport":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.rittersport);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);


                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.rittersport.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.ruttersport-store.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Food and chocolate");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("Ritter Sport is a brand of chocolate from the Alfred Ritter GmbH & Co. KG. Company which is headquartered in Waldenbuch, Germany.\n" +
                        "\n" +
                        "Each 100 gram square-shaped bar is divided into 16 smaller squares, creating a four-by-four pattern. In 2013 the company introduced a new version that is divided into 9 smaller squares using a three-by-three pattern. Large bars weighing 250 grams and 16.5 gram mini bars are also available, although in fewer varieties.");
                break;
            }

            case "pepsi":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.pepsi);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.pepsi.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.pepsistore.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Food and drinks");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("Pepsi is a carbonated soft drink produced and manufactured by PepsiCo. Originally created and developed in 1893 by Caleb Bradham and introduced as Brad's Drink, it was renamed as Pepsi-Cola on August 28, 1898, and then as Pepsi in 1961.");
                break;
            }

            case "ford":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.ford);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.ford.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.fordstore.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Cars and motorbikes");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("BMW (Bayerische Motoren Werke in German, or Bavarian Motor Works in English) is a German multinational company which currently produces automobiles and motorcycles, and also produced aircraft engines until 1945.\n" +
                        "\n" +
                        "The company was founded in 1916 and has its headquarters in Munich, Bavaria. BMW produces motor vehicles in Germany, Brazil, China, India, South Africa and the United States. In 2015, BMW was the world's twelfth largest producer of motor vehicles, with 2,279,503 vehicles produced.[2] The Quandt family are long-term shareholders of the company, with the remaining stocks owned by public float.");
                break;
            }

            case "cocacola":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.cocacola);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.cocacola.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.cocacolastore.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Food and drinks");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("Coca-Cola, or Coke, is a carbonated soft drink produced by The Coca-Cola Company. Originally intended as a patent medicine, it was invented in the late 19th century by John Pemberton and was bought out by businessman Asa Griggs Candler, whose marketing tactics led Coca-Cola to its dominance of the world soft-drink market throughout the 20th century. The drink's name refers to two of its original ingredients, which were kola nuts (a source of caffeine) and coca leaves. The current formula of Coca-Cola remains a trade secret, although a variety of reported recipes and experimental recreations have been published.");
                break;
            }

            case "stellartois":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.stellaartois);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.ups.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.ups.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Post and delivery");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("United Parcel Service");
                break;
            }

            case "fedex":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.fedex);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.fedex.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.fedexstore.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Post and delivery");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("FedEx Corporation is an American multinational courier delivery services company headquartered in Memphis, Tennessee. The name \"FedEx\" is a syllabic abbreviation of the name of the company's original air division, Federal Express (now FedEx Express), which was used from 1973 until 2000. The company is known for its overnight shipping service and pioneering a system that could track packages and provide real-time updates on package location (to help in finding lost packages), a feature that has now been implemented by most other carrier services");
                break;
            }

            case "tsingtao":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.tsingtao);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.tsingtao.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.tsingtao.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Beer brewery");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("Tsingtao Brewery Co., Ltd. (simplified Chinese: 青岛啤酒厂; traditional Chinese: 青島啤酒廠; pinyin: Qīngdǎo píjiǔchǎng; German: Germania-Brauerei) is China's second largest brewery. It was founded in 1903 by German settlers and now claims about 15% of domestic market share.[3] The beer is produced in Qingdao in Shandong province (and more recently in other breweries owned by the company as well), and it gets its name from the old École française d'Extrême-Orient transliteration of the city's name.[not verified in body] The beer's present-day logo displays an image of Zhan Qiao, a famous pier on Qingdao's southern shore");
                break;
            }

            case "fosters":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.fosters);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.ups.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.ups.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Post and delivery");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("United Parcel Service");
                break;
            }


            case "esso":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.esso);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.esso.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.esso-store.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Oil, fuel");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("Esso is a trading name for ExxonMobil and its related companies. The name is a phonetic version of the initials of the pre 1911 Standard Oil (SO = Esso),[1] and as such became the focus of much litigation and regulatory restriction in the United States. In 1972, it was largely replaced in the U.S. by the Exxon brand after it bought Humble Oil, while Esso remained widely used elsewhere.");
                break;
            }

            case "shell":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.shell);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);


                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.shell.us");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.shellstore.us");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Oil and gas");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("Shell Oil Company is the United States-based wholly owned subsidiary of Royal Dutch Shell, a multinational \"oil major\" of Anglo-Dutch origins, which is amongst the largest oil companies in the world. Approximately 22,000 Shell employees are based in the U.S. The U.S. headquarters are in Houston, Texas. Shell Oil Company, including its consolidated companies and its share in equity companies, is one of America's largest oil and natural gas producers, natural gas marketers, gasoline marketers and petrochemical manufacturers.");
                break;
            }

            case "dhl":
            {
                brandImage = BitmapFactory.decodeResource(getResources(), R.drawable.dhl);
                ImageView imageView = (ImageView) findViewById(R.id.brand_image);
                imageView.setImageBitmap(brandImage);

                TextView website1 = (TextView)findViewById(R.id.website_link);
                website1.setText("www.dhl.com");
                TextView website2 = (TextView)findViewById(R.id.second_website_link);
                website2.setText("www.dhl-store.com");
                TextView briefDescription = (TextView)findViewById(R.id.brief_description);
                briefDescription.setText("Post and delivery");
                TextView description = (TextView)findViewById(R.id.info);
                description.setText("DHL Express[2] is a division of the German logistics company Deutsche Post DHL providing international courier, parcel and express mail services. Deutsche Post DHL is the world's largest logistics company operating around the world,[3] particularly in sea and air mail.[4][5]\n" +
                        "\n" +
                        "Founded in the United States in 1969 to deliver documents between San Francisco and Honolulu, the company expanded its service throughout the world by the late 1970s. The company was primarily interested in offshore and inter-continental deliveries, but the success of FedEx prompted their own intra-US expansion starting in 1983.");
                break;
            }

        }

    }
}

