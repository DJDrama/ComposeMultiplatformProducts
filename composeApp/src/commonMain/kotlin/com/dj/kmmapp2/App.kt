package com.dj.kmmapp2

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seiko.imageloader.Image
import com.seiko.imageloader.rememberImagePainter
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmpapp2.composeapp.generated.resources.Res
import kmpapp2.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    MaterialTheme {
        AppContent(homeViewModel = HomeViewModel())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(homeViewModel: HomeViewModel) {
    val products = homeViewModel.products.collectAsStateWithLifecycle()
    BoxWithConstraints {
        val scope = this
        val maxWidth = scope.maxWidth
        var columns = 2
        var modifier = Modifier.fillMaxWidth()
        if (maxWidth > 840.dp) {
            columns = 3
            modifier = Modifier.widthIn(max = 1080.dp)
        }
        val scrollState = rememberLazyGridState()
        val coroutineScope = rememberCoroutineScope()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                state = scrollState,
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.draggable(orientation = Orientation.Vertical,
                    state = rememberDraggableState { delta ->
                        coroutineScope.launch {
                            scrollState.scrollBy(-delta)
                        }
                    })
            ) {
                item(span = {
                    GridItemSpan(currentLineSpan = columns)
                }) {
                    SearchBar(
                        modifier = Modifier.fillMaxWidth(),
                        query = "",
                        active = false,
                        onActiveChange = {},
                        onQueryChange = {},
                        onSearch = {},
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                        },
                        placeholder = {
                            Text("Search Products")
                        }
                    ) {}
                }
                items(items = products.value, key = { item ->
                    item.id.toString()
                }) {
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        elevation = 2.dp
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            val painter = rememberImagePainter(url = it.image.toString())
                            Image(
                                painter = painter, contentDescription = "${it.title}'s Image",
                                modifier = Modifier.height(130.dp).padding(8.dp)
                            )
                            Text(
                                text = it.title.toString(),
                                modifier = Modifier.padding(horizontal = 16.dp)
                                    .heightIn(min = 40.dp),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    textAlign = TextAlign.Start,
                                    text = "$${it.price}",
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                        .heightIn(min = 40.dp),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}