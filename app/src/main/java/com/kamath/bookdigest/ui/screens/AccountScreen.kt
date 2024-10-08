package com.kamath.bookdigest.ui.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kamath.bookdigest.ui.screens.common.ButtonsRow
import com.kamath.bookdigest.ui.screens.common.FollowersRow
import com.kamath.bookdigest.ui.screens.common.ProfileImageAndName
import com.kamath.bookdigest.ui.screens.common.SubList

@Composable
fun AccountScreen(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        ProfileImageAndName(name = "Your Name", username = "yourusername")
        Spacer(modifier = Modifier.height(16.dp))
        ButtonsRow(
            onEditClick = { /* Handle edit button click */ },
            onMyBooksClick = { /* Handle my books button click */ }
        )
        Spacer(modifier = Modifier.height(16.dp))
        FollowersRow(bookListed = 100, following = 200, followers = 300)
        Spacer(modifier = Modifier.height(16.dp))

        SubList(
            sectionTitle = "Book Listing",
            items = listOf("Book 1", "Book 2", "Book 3"), // Example book items
            onItemClick = { itemName ->
                // Handle item click
            }
        )

    }
}

