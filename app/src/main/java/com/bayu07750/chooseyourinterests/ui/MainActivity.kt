@file:OptIn(ExperimentalLayoutApi::class)

package com.bayu07750.chooseyourinterests.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bayu07750.chooseyourinterests.R
import com.bayu07750.chooseyourinterests.data.InterestsRepositoryImp
import com.example.compose.AppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels(
        factoryProducer = {
            viewModelFactory {
                initializer {
                    val repository = InterestsRepositoryImp()
                    MainViewModel(repository)
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val interests by viewModel.interests.collectAsStateWithLifecycle()
                InterestsScreen(
                    interests = interests,
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun InterestsScreen(
    interests: List<InterestState>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .systemBarsPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 16.dp),
    ) {
        Text(
            text = stringResource(R.string.choose_your_interests),
            style = MaterialTheme.typography.displaySmall.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = stringResource(R.string.get_better_movies_recommendations),
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.W300,
                fontSize = 18.sp,
            )
        )
        Spacer(modifier = Modifier.height(32.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            interests.forEach { interest ->
                InterestButton(
                    interest = interest,
                )
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ElevatedButton(
                modifier = Modifier
                    .weight(1f),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(6.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 0.dp
                )
            ) {
                Text(text = stringResource(R.string.skip))
            }
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(6.dp),
            ) {
                Text(text = stringResource(R.string.next))
            }
        }
    }
}

@Composable
private fun InterestButton(
    interest: InterestState,
    modifier: Modifier = Modifier,
) {
    androidx.compose.runtime.key(interest.id) {
        Surface(
            modifier = modifier
                .toggleable(
                    value = interest.checked,
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null,
                    enabled = true,
                    role = Role.Checkbox,
                    onValueChange = {
                        interest.checked = it
                    }
                ),
            tonalElevation = 2.dp,
            shadowElevation = 2.dp,
            shape = CircleShape,
            color = if (interest.checked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
        ) {
            Text(
                text = interest.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
    }
}