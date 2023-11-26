package com.ppgm.anydon.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ppgm.anydon.R
import com.ppgm.anydon.ui.theme.AnydonTheme
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class Post(
    val username: String,
    val content: String,
    val postTime: LocalDateTime = LocalDateTime.now().minusMinutes(1)
)
@Composable
fun SocialMediaPost(
    username: String,
    postContent: String,
    postTime: LocalDateTime = LocalDateTime.now().minusMinutes(1)
) {
    val post = Post(username, postContent, postTime)

    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_user_profile), // 画像のアイコンに対応するリソースを指定
                contentDescription = "User Profile",
                tint = Color.Unspecified // アイコンの色をデフォルトに設定
            )
            Text(
                text = post.username,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = calculatePostTime(post.postTime),
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Text(
            text = post.content,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

fun calculatePostTime(postTime: LocalDateTime): String {
    val minutesAgo = ChronoUnit.MINUTES.between(postTime, LocalDateTime.now())
    return "${minutesAgo}分前"
}

@Preview
@Composable
fun PreviewFunc(){
    AnydonTheme {
        SocialMediaPost(username = "ppgm", postContent = "これはPOST")
    }
}