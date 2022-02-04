package com.harrycampaz.core.data.models

import com.harrycampaz.core.utils.getFakeData
import com.harrycampaz.core.utils.getFakeDataPostsModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
class DataPostsModelTest {

    @Test
    fun `GIVEN DataPostsModel List WHEN toEntity THEN DataPostsEntity`(){
        val dataPosts = getFakeDataPostsModel()
        val  dataPostsEntity = dataPosts.toEntity()

        assertEquals(dataPosts.title, dataPostsEntity.title)
        assertEquals(dataPosts.author_fullname, dataPostsEntity.author_fullname)
        assertEquals(dataPosts.thumbnail, dataPostsEntity.thumbnail)
        assertEquals(dataPosts.num_comments, dataPostsEntity.num_comments)
    }

    @Test
    fun `GIVEN List of Children List WHEN toEntityList THEN map list of DataPostsEntity`(){
        val children = getFakeData().children
        val  dataPostsListEntity = children.toListEntity()


        assertTrue(dataPostsListEntity.isNotEmpty())

        assertEquals(dataPostsListEntity.first().title, children.first().data.title)
        assertEquals(dataPostsListEntity.first().author_fullname, children.first().data.author_fullname)
        assertEquals(dataPostsListEntity.first().thumbnail, children.first().data.thumbnail)
        assertEquals(dataPostsListEntity.first().num_comments, children.first().data.num_comments)
    }
}