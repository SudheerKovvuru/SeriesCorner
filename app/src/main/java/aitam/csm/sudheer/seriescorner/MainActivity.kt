package aitam.csm.sudheer.seriescorner

import aitam.csm.sudheer.seriescorner.ui.theme.Purple40
import aitam.csm.sudheer.seriescorner.ui.theme.Purple80
import aitam.csm.sudheer.seriescorner.ui.theme.PurpleGrey80
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import aitam.csm.sudheer.seriescorner.ui.theme.SeriesCornerTheme
import android.provider.ContactsContract.Data
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SeriesCornerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "main") {
                        composable("main") { Series(navController) }
                        composable("details/{title}/{img}/{desc}",
                            arguments=listOf(
                                navArgument("title"){type= NavType.StringType},
                                navArgument("img"){type=NavType.IntType},
                                navArgument("desc"){type=NavType.StringType}
                            )
                        ) { backStackEntry->
                            val title=backStackEntry.arguments?.getString("title")
                            val img=backStackEntry.arguments?.getInt("img")
                            val desc= backStackEntry.arguments?.getString("desc")
                            Details(title,img,desc)
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun CardSection(image:Int, name:String,rating:String,navController: NavController,desc:String)
{
    Card(
        modifier= Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(MaterialTheme.shapes.small)
    ){
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clip(MaterialTheme.shapes.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row {
                Image(
                    painter = painterResource(image),
                    contentDescription = name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(10.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight()
                )
                {
                    Text(
                        text = name,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 20.sp,
                        style = MaterialTheme.typography.displayLarge,
                    )
                    Text(
                        text = "imdb: ${rating}",
                        fontSize = 16.sp,
                        lineHeight = 30.sp,
                        style = MaterialTheme.typography.displayMedium,
                    )
                }
            }
            IconButton(onClick = { navController.navigate("details/$name/$image/$desc")}) {
                Icon(
                    Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(30.dp)
                )
            }
        }
    }
}
@Composable
fun Top()
{
    Card(
        modifier= Modifier
            .fillMaxWidth()
            .height(60.dp)
        ){
        Text(text ="Series Corner", fontSize = 35.sp, fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium,
            modifier= Modifier
                .fillMaxWidth()
        )

    }
}
@Composable
fun Series(navController: NavController)
{
    Scaffold(
        topBar = {Top()},
        modifier = Modifier.padding(top = 40.dp)
    ) {
        LazyColumn(
            contentPadding = it
        ){
            items(DataSource().loadData()){
                item ->  CardSection(item.image,item.title,item.rating,navController,
                stringResource( item.desc))
            }
        }
    }

}
@Composable
fun Details(title:String?,img:Int?,desc:String?)
{
    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ){
        Top()
        img?.let {
            Image(
                painter = painterResource(id=it),
                contentDescription = "passed image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(400.dp)
                    .clip(MaterialTheme.shapes.small)
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title?:"no found",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium,
        )
        Text(
            text=desc?:"data not found",
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            textAlign = TextAlign.Justify,
            )
    }
}
@Preview(showBackground = true)
@Composable
fun SeriesCornerPreview() {
    SeriesCornerTheme {
        val navController = rememberNavController()
        Series(navController = navController)
    }
}