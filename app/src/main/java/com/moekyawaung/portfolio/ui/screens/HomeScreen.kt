package com.moekyawaung.portfolio.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import com.moekyawaung.portfolio.theme.*

@Composable
fun HomeScreen(
    name: String = "Moe Kyaw Aung",
    tagline: String = "⭐ ANDROID SENIOR DEVELOPER ⭐",
    roles: List<String> = listOf(
        "Android Developer",
        "Kotlin Engineer",
        "Jetpack Compose Specialist",
        "Firebase Builder"
    )
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Tagline
        Text(
            text = tagline,
            style = MaterialTheme.typography.button,
            color = NeonYellow,
            letterSpacing = LetterSpacing.em,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        )
        
        // Hero Name with Gradient
        Text(
            text = name,
            style = MaterialTheme.typography.h2,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            brush = Brush.horizontalGradient(
                colors = listOf(NeonCyan, NeonPink, NeonYellow)
            )
        )
        
        // Animated Typing Effect
        AnimatedTypingText(roles = roles)
        
        // Stats Section
        StatsSection()
        
        // Action Buttons
        ActionButtons()
    }
}

@Composable
fun AnimatedTypingText(roles: List<String>) {
    var currentIndex by remember { mutableStateOf(0) }
    var isDeleting by remember { mutableStateOf(false) }
    var currentText by remember { mutableStateOf("") }
    
    LaunchedEffect {
        while (true) {
            val currentRole = roles[currentIndex]
            
            if (!isDeleting) {
                // Typing
                if (currentText.length < currentRole.length) {
                    currentText = currentRole.substring(0, currentText.length + 1)
                    delay(70)
                } else {
                    // Pause at end
                    isDeleting = true
                    delay(900)
                }
            } else {
                // Deleting
                if (currentText.length > 0) {
                    currentText = currentRole.substring(0, currentText.length - 1)
                    delay(45)
                } else {
                    isDeleting = false
                    currentIndex = (currentIndex + 1) % roles.size
                }
            }
        }
    }
    
    Text(
        text = currentText + "▍",
        style = MaterialTheme.typography.body1,
        color = NeonCyan,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(vertical = 16.dp)
    )
}

@Composable
fun StatsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatItem(value = "12+", label = "Years Experience")
        StatItem(value = "3K+", label = "Apps Built")
        StatItem(value = "22", label = "Repositories")
        StatItem(value = "100%", label = "Satisfaction")
    }
}

@Composable
fun StatItem(value: String, label: String) {
    Column(
        modifier = Modifier
            .background(
                color = SurfaceDark.copy(alpha = 0.7),
                shape = RoundedCornerShape(22.dp)
            )
            .padding(20.dp)
            .width(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold,
            color = NeonCyan
        )
        Text(
            text = label,
            style = MaterialTheme.typography.body2,
            color = TextMuted,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun ActionButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = { /* Navigate to Contact */ },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = NeonCyan,
                contentColor = BackgroundDark
            )
        ) {
            Text("Hire Me")
        }
        
        Button(
            onClick = { /* Navigate to Projects */ },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = SurfaceDark2,
                contentColor = TextPrimary
            ),
            border = ButtonDefaults.buttonBorder(
                width = 1.dp,
                color = BorderLight
            )
        ) {
            Text("View Projects")
        }
    }
}
