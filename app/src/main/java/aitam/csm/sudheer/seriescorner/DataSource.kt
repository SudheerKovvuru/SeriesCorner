package aitam.csm.sudheer.seriescorner

import aitam.csm.sudheer.seriescorner.model.Seriesdata
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

class DataSource {
    fun loadData():List<Seriesdata>
    {
        return listOf<Seriesdata>(
            Seriesdata(R.drawable.img,"Game of Thrones","9.1", R.string.got),
            Seriesdata(R.drawable.img_1,"Breaking Bad","9.5",R.string.brba),
            Seriesdata(R.drawable.img_2,"House of Dragon","8.4",R.string.hotd),
            Seriesdata(R.drawable.img_3,"Money Heist","8.6",R.string.mh),
            Seriesdata(R.drawable.img_4,"Dark","8.9",R.string.dark),
            Seriesdata(R.drawable.img_5,"Black Mirror","8.3",R.string.bckmir),
            Seriesdata(R.drawable.img_6,"Stranger things","8.7",R.string.strth),
        )
    }
}