package com.bombadu.userdirectory.di

import android.content.Context
import androidx.room.Room
import com.bombadu.userdirectory.data.LocalDao
import com.bombadu.userdirectory.data.LocalDatabase
import com.bombadu.userdirectory.repository.DefaultUserRepository
import com.bombadu.userdirectory.repository.UserRepository
import com.bombadu.userdirectory.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    //Adding a DB to Module
    @Singleton
    @Provides
    fun provideLocalDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, LocalDatabase::class.java, DATABASE_NAME).build() //DATABASE_NAME (in Constants File)

    @Singleton
    @Provides
    fun provideLocalDao(
        database: LocalDatabase
    ) = database.localDao()

    @Singleton
    @Provides
    fun provideDefaultUserRepository(
        dao: LocalDao
    ) = DefaultUserRepository(dao) as UserRepository


    //More Examples not needed for this app
   /* @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
    )

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    } */
}