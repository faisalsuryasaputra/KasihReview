package com.example.kasihreview.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kasihreview.Model.ReviewResponse
import com.example.kasihreview.Model.VoteRequestDTO
import com.example.kasihreview.R
import com.example.kasihreview.ViewModel.KRviewModel
import com.example.kasihreview.ui.theme.OpenSans

@Composable
fun ulasanPrev(
    review: ReviewResponse,
    modifier: Modifier,
    VM: KRviewModel
){
    val userVotes by VM.userVoteList.collectAsState()
    val account by VM.currentSession.collectAsState()
    var upvote by remember { mutableIntStateOf(R.drawable.upvoteunfilled) }
    var downvote by remember { mutableIntStateOf(R.drawable.downvoteunfilled) }

    LaunchedEffect(Unit) {
        account.id?.let { VM.getVotesByMovieGoerId(review.movieId, it) }
    }

    LaunchedEffect(review.upvotes) {
        account.id?.let { VM.getReviewByMovieGoerId(it) }
    }

    LaunchedEffect(review.downvotes) {
        account.id?.let { VM.getReviewByMovieGoerId(it) }
    }

    val voteUser = userVotes.voteList.find { it.reviewId == review.reviewId }

    var ulasanPendek: String = review.content
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFE9A6A6).copy(0.05f))
            .padding(5.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(imageVector = ImageVector.vectorResource(R.drawable.genericavatar), contentDescription = " "
                , modifier = Modifier
//                    .height(50.dp)
//                    .width(50.dp)
                    )

            Text(
                text = "Ulasan dari",
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                color = Color.White.copy(0.5f)
            )

            Spacer(modifier = Modifier
                .width(5.dp))

            Text(
                text = review.reviewerName,
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFE9A6A6)
            )
        }

        if (review.content.length > 80) {

            ulasanPendek = "${review.content.substring(0,80)}..."
        }

        Text(
            text = ulasanPendek,
            fontFamily = OpenSans,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )

        review.movieTitle?.let {
            Text(
                text = it,
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFE9A6A6)
            )
        }

        Text(
            text = "Baca Selengkapnya >",
            fontFamily = OpenSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = Color(0xFF9C4A8B),
            modifier = modifier
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (voteUser != null) {
                if (voteUser.voteType == "upvote") {
                    upvote = R.drawable.upvotefilled
                    downvote =R.drawable.downvoteunfilled
                }else if (voteUser.voteType == "downvote") {
                    downvote = R.drawable.downvotefilled
                    upvote = R.drawable.upvoteunfilled
                }
            }
            Image(
                imageVector = ImageVector.vectorResource(upvote),
                contentDescription = "",
                modifier = Modifier
                    .clickable {
                        val hasVotedOrNot = userVotes.voteList.any { it.reviewId == review.reviewId }
                        if (!hasVotedOrNot) {
                            account.id?.let { VoteRequestDTO("upvote", it) }
                                ?.let { VM.postReviewVote(it, review.reviewId) }
                        }else {
                            val vote = userVotes.voteList.find { it.reviewId == review.reviewId }
                            if (vote != null) {
                                println(vote.voteId)
                                println(review.reviewId)
                                println("upvote")
                                VM.patchUserVote(vote.voteId,review.reviewId, "upvote")
                            }
                        }

                    }
            )

            Text(
                text = review.upvotes.toString(),
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                color = Color(0xFF9C4A8B),
            )

            Image(
                imageVector = ImageVector.vectorResource(downvote),
                contentDescription = "",
                modifier = Modifier
                    .clickable {
                        val hasVotedOrNot = userVotes.voteList.any { it.reviewId == review.reviewId }
                        if (!hasVotedOrNot) {
                            account.id?.let { VoteRequestDTO("downvote", it) }
                                ?.let { VM.postReviewVote(it, review.reviewId) }
                        }else {
                            val vote = userVotes.voteList.find { it.reviewId == review.reviewId }
                            if (vote != null) {
                                VM.patchUserVote(vote.voteId,review.reviewId, "downvote")
                            }
                        }
                    }
            )

            Text(
                text = review.downvotes.toString(),
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                color = Color(0xFF9C4A8B),
            )
        }
    }
}