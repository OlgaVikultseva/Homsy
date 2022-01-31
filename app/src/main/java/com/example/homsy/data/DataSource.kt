package com.example.homsy.data

import com.example.homsy.R
import com.example.homsy.presentation.choicebuilding.adapter.BuildingItem
import com.example.homsy.presentation.choicebuilding.adapter.CategoryItem

class DataSource {

    private val buildingList = listOf(
        BuildingItem(
            1,
            "Victoria Village",
            "Apartment",
            "Warm apartment ideal for business trip, families or couple. The apartment is located in an old historical building and includes spacious kitchen, separate toilet at the entrance of apartment, cozy bedroom with a nice bathroom and lovely living room. Great WIFI network.",
            "\$3000",
            R.drawable.apartment1,
            false
        ),
        BuildingItem(
            2,
            "9 Rosamond Street",
            "House",
            "The apartment has a floor space of 60 square meters and it’s located on the first floor, and in it you will find a spacious living room with a stylish sitting area with smart TV with Netflix included, fully equipped kitchen and a dining. The sofa extends as a bed suitable for two people. The bedroom comes with the most comfortable bed with a choice of pillows and blackout curtains for the perfect night in after a long day. There are separate bathroom with a shower and a toilet that come with all the necessary toiletries.",
            "\$4600",
            R.drawable.house1,
            false
        ),
        BuildingItem(
            3,
            "85 Wood Street",
            "House",
            "This Apartment is designed in a Modern and Practical elements which provides maximum comfort and the best experience during your visit. The Apartment has contemporary style decoration in combination with original wooden elements and come together with shades of grey, beige and white to give the studio an exquisite elegance. Located in 2nd floor of a historical residential house is close to main points of interest.",
            "\$5800",
            R.drawable.house3,
            false
        ),
        BuildingItem(
            4,
            "6 Tamola Park Court",
            "Apartment",
            "Ideal for families, couples, or business trip is located right next to the river. This modern fully equipped apartment includes an amazing spacious living room together with cozy dining area, two beautiful spacious bedrooms, fully equipped kitchen, modern bathroom and separate toilet.",
            "\$2600",
            R.drawable.apartment2,
            false
        ),
        BuildingItem(
            5,
            "34 Willowbrook Road",
            "Apartment",
            "This newly renovated, fully furnished studio features a bedroom with a queen bed and sofa-bed on the upper floor, a spacious and cozy living room with a comfy sofa-bed facing a TV with Netflix on a first floor, a small work table, a dining table, a spacious bathroom with a bathtub and a brand new fully-equipped kitchen. A wall safe and luggage holder also available. The apartment can accommodate comfortably up to 4 persons with 5 people as maximum capacity.",
            "\$3100",
            R.drawable.apartment3,
            false
        ),
        BuildingItem(
            6,
            "Mono Amaranth Town",
            "House",
            "Beautiful two bedroom Old Town apartment with magnificent views with spacious terrace and fully equipped kitchen and two bathrooms. One of the bathroom is ensuite. The Apartment has a beautiful and spacious terrace with views of all major historical sights. The Apartment has free high-speed WIFI, fully equipped kitchen with dishwasher, TV with international channels, air-condition and a new modern interior combined with beautifully preserved old wooden floors.",
            "\$4000",
            R.drawable.house2,
            false
        ),
    )

    fun getCategoryList(): List<CategoryItem> =
        listOf(
            CategoryItem(1, "Apartment", false),
            CategoryItem(2, "House", false),
            CategoryItem(3, "Near you!", true),
            CategoryItem(4, "Popular", false)
        )

    fun getBuildingList(): List<BuildingItem> =
        buildingList

    fun getBuilding(buildingId: Int): BuildingItem? =
        buildingList.find { it.id == buildingId }
}