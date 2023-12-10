package com.ppgm.anydon.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDateTime

class TootList {
    @Composable
    fun SocialMediaFeed(posts: List<Post>) {
        LazyColumn {
            items(posts) { post ->
                SocialMediaPost(
                    username = post.username,
                    postContent = post.content,
                    postTime = post.postTime
                )
            }
        }
    }@Preview
    @Composable
    fun Preview(){
        SocialMediaFeed(posts = listOf(
            Post("ppgm_p", "とうこうだよ", LocalDateTime.now()),
            Post("halti", "ずちー", LocalDateTime.now()),
            Post("zucci", "ぽぽがみぽー", LocalDateTime.now()),
        ))
    }
}
