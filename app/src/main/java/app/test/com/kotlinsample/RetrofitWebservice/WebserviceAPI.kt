package app.test.com.kotlinsample.RetrofitWebservice

import app.test.com.kotlinsample.Pojo.Book
import retrofit.Callback
import retrofit.client.Response
import retrofit.http.Field
import retrofit.http.GET
import retrofit.http.POST
import retrofit.http.Part
import retrofit.mime.TypedFile
import java.io.File


/**
 * Created by selvakumark on 23-08-2017.
 */

interface WebserviceAPI {

    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */
    @GET("/bins/jul6f")
    fun getBooks(response: Callback<List<Book>>)

    @POST("/bins/jul6f")
    fun getBook(response: Callback<List<Book>>)

    @POST("/GetDetailWithMonthWithCode")
    fun getLandingPageReport(@Field("code") code: File,
                             @Field("monthact") monthact: String,
                             cb: Callback<List<Book>>)

    @POST("/en/Api/Results/UploadFile")
    fun UploadFile(@Part("file") file: TypedFile, @Part("folder") folder: String, callback: Callback<Response>)
}