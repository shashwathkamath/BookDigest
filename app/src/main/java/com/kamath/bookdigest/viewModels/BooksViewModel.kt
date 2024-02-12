package com.kamath.bookdigest.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamath.bookdigest.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val bookRepository: BookRepository):ViewModel(){

    private val TAG = "BOOKS_VIEW_MODEL"
    fun searchBookByIsbn(isbn:String){
        Log.d(TAG, "searchBookByIsbn: ")

        viewModelScope.launch {
            try {
                val bookDetails = bookRepository.getBookDetails(isbn)
                Log.d(TAG, "searchBookByIsbn: ${bookDetails}")
            }catch (e:Exception){
                Log.d(TAG, "searchBookByIsbn: Issue with calling Books API ${e}")
            }
        }
    }
}