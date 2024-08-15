package com.propsimlify.app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomePostInfo(
    val id: Int,
    val image: String? = null,
    val name: String? = null,
    val address: String? = null,
    val bedrooms: Int? = null,
    val bathroom: Int? = null,
    val sqft: Int? = null,
    val rating: Double? = null,
    val apartmentRating: Double? = null,
    val roi: String? = null,
    val price: String? = null,
    var highDemand: Boolean? = false,
    var avgRentalIncome: String? = null,
    var description: String? = null,
    var totalAmount: String? = null,
    var isLiked: Boolean = false,
    var isDoubleHeart: Boolean = false,
    var isHeartBreak: Boolean = false,
    var isDoubleHeartBreak: Boolean = false,
) : Parcelable

@Parcelize
data class HomePostCategoryInfo(
    val id: Int? = null, val name: String? = null, var isSelected: Boolean? = false
) : Parcelable


@Parcelize
data class LiveDestinationInfo(
    val id: Int? = null, val name: String? = null, val image: String? = null, var isSelected: Boolean? = false
) : Parcelable


@Parcelize
data class LifeStyleInfo(
    val id: Int? = null, val name: String? = null, val image: String? = null, var isSelected: Boolean = false
) : Parcelable


fun getHomePostList(): ArrayList<HomePostInfo> {
    val listOfHomePage = arrayListOf<HomePostInfo>()
    listOfHomePage.add(
        HomePostInfo(
            1,
            "first_card_bg",
            "Vida Residences",
            "Emaar Blvd - Downtown Dubai - Dubai",
            3,
            2,
            1577,
            5.0,
            5.0,
            "8.0 %",
            "Fair",
            highDemand = true,
            avgRentalIncome = "250,000 AED/Year",
            description = "Surrounding you with stunning and unique wide views of the Dubai Marina, beaches and gorgeous nautical inspired architecture, Vida Residences Dubai Marina promises a relaxing, healthy lifestyle like no other.",
            totalAmount = "AED 7,142,888",
            isLiked = false
        )
    )

    listOfHomePage.add(
        HomePostInfo(
            2,
            "second_card_bg",
            "BURJ DAMAN",
            "Al Mustaqbal St - Za'abeel ,Dubai",
            3,
            3,
            2024,
            5.0,
            5.0,
            "7.0 %",
            "Fair",
            highDemand = true,
            avgRentalIncome = "320,000 AED/Year",
            description = "We are pleased to offer this exclusive one bedroom in Burj Daman. It's located on a high floor and has a great living room with amazing view over the city.",
            totalAmount = "AED 8,642,888",
            isLiked = false
        )
    )
    listOfHomePage.add(
        HomePostInfo(
            3,
            "third_card_bg",
            "City Walk",
            "Building 9, UAE, Dubai, Al Wasl, City Walk",
            3,
            3,
            1956,
            4.8,
            5.0,
            "7.50 %",
            "Fair",
            highDemand = true,
            avgRentalIncome = "220,000 AED/Year",
            description = "Situated in the core of this popular region, City Walk 9 has everything directly at your doorstep. With the top of the line Roxy Cinemas, to the gaming focal Hub Zero, and obviously all the best eateries, bistros and shopping brands only minutes away.",
            totalAmount = "AED 5,200,000",
            isLiked = false
        )
    )

    listOfHomePage.add(
        HomePostInfo(
            4,
            "fouth_card_bg",
            "23 Marina",
            "23 Marina, UAE, Dubai, Dubai Marina",
            3,
            4,
            1856,
            4.7,
            4.7,
            "8.0 %",
            "Fair",
            highDemand = true,
            avgRentalIncome = "200,000 AED/ Year",
            description = "The spacious living area comes with a natural light from the large floor-to-ceiling windows, offering stunning views of the Dubai Marina skyline. The three bedrooms are generously sized and each come with their own en-suite bathrooms. The master bedroom also features a private balcony. The maid's room is located near the kitchen and has its own bathroom.",
            totalAmount = "AED 4,000,000",
            isLiked = false
        )
    )
    listOfHomePage.add(
        HomePostInfo(
            5,
            "five_card_bg",
            "Marina Gate",
            "Marina Gate 1, UAE, Dubai, Dubai Marina, Marina Gate",
            3,
            2,
            1381,
            4.5,
            4.5,
            "7.0 %",
            "Fair",
            highDemand = true,
            avgRentalIncome = "160,000 AED / Year",
            description = "This Marina Gate property is well-built, spacious, and one-of-a-kind, allowing freshness and positivity to flow into each of the rooms. The floor-to-ceiling window lets natural light brighten up your space, and the waterfront views, delight your eyes. This apartment is perfect for families, as well as individuals, who are looking for a space to call home.",
            totalAmount = "AED 3,400,000",
            isLiked = false
        )
    )
    return listOfHomePage
}


fun getHomePostCategoryList(): ArrayList<HomePostCategoryInfo> {
    val listOfHomeCategory = arrayListOf<HomePostCategoryInfo>()
    listOfHomeCategory.add(HomePostCategoryInfo(1, "Property Details", true))
    listOfHomeCategory.add(HomePostCategoryInfo(2, "Location", false))
    listOfHomeCategory.add(HomePostCategoryInfo(3, "Amenities", false))
    return listOfHomeCategory
}


fun getLiveDestinationList(): ArrayList<LiveDestinationInfo> {
    val listOfLiveDestination = arrayListOf<LiveDestinationInfo>()
    listOfLiveDestination.add(LiveDestinationInfo(1, "Ajman", "ic_ajman", false))
    listOfLiveDestination.add(LiveDestinationInfo(2, "Abu Dhabi", "ic_abu_dhabi", false))
    listOfLiveDestination.add(LiveDestinationInfo(3, "Dubai", "ic_dubai", true))
    listOfLiveDestination.add(LiveDestinationInfo(4, "Sharjah", "ic_sharjah", false))
    listOfLiveDestination.add(LiveDestinationInfo(5, "Umm Al Quwain", "ic_umm_al_quwain", false))
    listOfLiveDestination.add(LiveDestinationInfo(6, "Ras Al Khaimah", "ic_ras_al_khaimah", false))
    listOfLiveDestination.add(LiveDestinationInfo(7, "Fujairah", "ic_fujairah", false))
    return listOfLiveDestination
}


fun getPropertyTypeList(): ArrayList<LiveDestinationInfo> {
    val listOfLiveDestination = arrayListOf<LiveDestinationInfo>()
    listOfLiveDestination.add(LiveDestinationInfo(1, "Townhouse", "ic_townhouse", false))
    listOfLiveDestination.add(LiveDestinationInfo(2, "Apartment", "ic_apartment", true))
    listOfLiveDestination.add(LiveDestinationInfo(3, "Vila", "ic_vila", false))
    return listOfLiveDestination
}

fun getLifeStyleList(): ArrayList<LifeStyleInfo> {
    val listOfLifeStyle = arrayListOf<LifeStyleInfo>()
    listOfLifeStyle.add(LifeStyleInfo(1, "Investment", "ic_investment", false))
    listOfLifeStyle.add(LifeStyleInfo(2, "Neighbourhood Growth", "ic_neighbourhood_growth", false))
    listOfLifeStyle.add(LifeStyleInfo(3, "Child Friendly", "ic_child_friendly", false))
    listOfLifeStyle.add(LifeStyleInfo(4, "Easy Commuting", "ic_easy_commuting", false))
    listOfLifeStyle.add(LifeStyleInfo(5, "Luxury Living", "ic_luxury_living", false))
    listOfLifeStyle.add(LifeStyleInfo(6, "Hassle Free Paperwork", "ic_hassle_free_paperwork", false))
    listOfLifeStyle.add(LifeStyleInfo(7, "Secluded Lifestyle", "ic_secluded_lifestyle", false))
    listOfLifeStyle.add(LifeStyleInfo(8, "Urban Lifestyle", "ic_urban_lifestyle", false))
    listOfLifeStyle.add(LifeStyleInfo(9, "Rental Income", "ic_rental_income", false))
    listOfLifeStyle.add(LifeStyleInfo(10, "Active Lifestyle", "ic_active_lifestyle", false))
    listOfLifeStyle.add(LifeStyleInfo(11, "Convenient Location", "ic_convenient_location", false))
    listOfLifeStyle.add(LifeStyleInfo(12, "Leisure Facilities", "ic_leisure_facilities", false))
    listOfLifeStyle.add(LifeStyleInfo(13, "Affordable Options", "ic_affordable_options", false))
    return listOfLifeStyle
}

sealed class HomePagePostInfoState {
    data class UserLikeClick(val postInfo: HomePostInfo) : HomePagePostInfoState()
    data class UserDoubleHeartClick(val postInfo: HomePostInfo) : HomePagePostInfoState()
    data class UserHeartBreakClick(val postInfo: HomePostInfo) : HomePagePostInfoState()
    data class UseDoublerHeartBreakClick(val postInfo: HomePostInfo) : HomePagePostInfoState()
}