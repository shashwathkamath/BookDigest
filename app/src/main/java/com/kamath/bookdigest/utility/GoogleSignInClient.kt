//package com.kamath.bookdigest.utility
//
//import android.content.Context
//import android.content.Intent
//import android.content.IntentSender
//import android.os.Build
//import android.service.autofill.UserData
//import androidx.annotation.RequiresApi
//import com.google.android.gms.auth.api.identity.BeginSignInRequest
//import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
//import com.google.android.gms.auth.api.identity.SignInClient
//import com.google.firebase.Firebase
//import com.google.firebase.auth.GoogleAuthProvider
//import com.google.firebase.auth.auth
//import com.kamath.bookdigest.R
//import com.kamath.bookdigest.data.model.SignInResult
//import com.kamath.bookdigest.data.model.UserData
//import kotlinx.coroutines.tasks.await
//import java.util.concurrent.CancellationException
//
//class GoogleSignInClient(
//    private val context: Context,
//    private val oneTapClient:SignInClient
//) {
//    private val auth = Firebase.auth
//
//    suspend fun signIN():IntentSender?{
//        val result = try{
//            oneTapClient.beginSignIn(
//                beginSignInRequest()
//            ).await()
//        }
//        catch (e:Exception){
//            e.printStackTrace()
//            if (e is CancellationException) throw e
//            null
//        }
//        return result?.pendingIntent?.intentSender
//    }
//
//    @RequiresApi(Build.VERSION_CODES.P)
//    suspend fun signInWithIntent(intent: Intent):SignInResult {
//        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
//        val googleIdToken = credential.googleIdToken
//        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken,null)
//        return try {
//            val user = auth.signInWithCredential(googleCredentials).await().user
//            SignInResult(
//                data = user?.run {
//                    UserData(
//                        userId = uid,
//                        username = displayName.toString(),
//                        profilePictureUrl = photoUrl.toString()
//                    )
//                },
//                errorMessage = null
//            )
//        }
//        catch (e:Exception){
//            e.printStackTrace()
//            if (e is CancellationException) throw e
//            SignInResult(
//                data = null,
//                errorMessage = e.message
//            )
//        }
//    }
//
//    private fun beginSignInRequest():BeginSignInRequest{
//        return BeginSignInRequest.Builder()
//            .setGoogleIdTokenRequestOptions(
//                GoogleIdTokenRequestOptions.builder()
//                    .setSupported(false)
//                    .setFilterByAuthorizedAccounts(false)
//                    .setServerClientId(context.getString(R.string.web_client_id))
//                    .build()
//            )
//            .setAutoSelectEnabled(true)
//            .build()
//    }
//
//     fun getSignedInUser():UserData? = auth.currentUser?.run {
//        UserData(
//            userId = uid,
//            username = displayName.toString(),
//            profilePictureUrl = photoUrl.toString()
//        )
//    }
//
//    suspend fun signOut(){
//        try {
//            oneTapClient.signOut().await()
//            auth.signOut()
//        }
//        catch (e:Exception){
//            e.printStackTrace()
//            if (e is CancellationException) throw e
//        }
//    }
//}
//
