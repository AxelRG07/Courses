package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTheme {
                TopicApp()
            }
        }
    }
}

@Composable
fun TopicApp() {
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(bottom = dimensionResource(R.dimen.padding_small)),
        color = MaterialTheme.colorScheme.background
    ) {
        TopicList(
            topicList = DataSource.topics,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.padding_small),
                top = dimensionResource(R.dimen.padding_small),
                end = dimensionResource(R.dimen.padding_small),
            )
        )
    }
}

@Composable
fun TopicCard(
    topic: Topic,
    modifier: Modifier = Modifier
) {
    Card (modifier = modifier) {
        Row {
            Box {
                Image(
                    painter = painterResource(topic.imageRes),
                    contentDescription = stringResource(topic.name),
                    modifier = Modifier
                        .height(68.dp)
                        .width(68.dp)
                        .padding(end = dimensionResource(R.dimen.padding_medium)),
                    contentScale = ContentScale.Crop
                )
            }
            Column (
                modifier = modifier
                    .padding(
                        0.dp,
                        dimensionResource(R.dimen.padding_medium),
                        dimensionResource(R.dimen.padding_medium),
                    ),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = stringResource(topic.name),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(bottom = dimensionResource(R.dimen.padding_small))
                )
                Row (
                    modifier = Modifier,
                    //horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                    )

                    Text(
                        text = topic.availableCourses.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Composable
fun TopicList(topicList: List<Topic>, modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier
    ){
        items(topicList) { topic ->
            TopicCard(topic)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicCardPreview() {
    CoursesTheme {
        TopicList(
            topicList = DataSource.topics
        )
    }

}