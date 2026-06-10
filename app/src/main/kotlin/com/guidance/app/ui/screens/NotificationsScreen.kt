package com.guidance.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen() {
    var notifications by remember {
        mutableStateOf(listOf(
            NotificationItem("تنبيه الجلسة", "جلسة مع أحمد محمد - اليوم الساعة 2 مساءً", "2 ساعة"),
            NotificationItem("طلب وصول جديد", "د. فاطمة طلبت الوصول لحالة محمود", "ساعة واحدة"),
            NotificationItem("تقدم الحالة", "تحسن ملحوظ ��ي حالة علي محمد", "6 ساعات"),
            NotificationItem("تذكير المتابعة", "لم تكمل متابعة حالة سارة", "يوم واحد")
        ))
    }
    
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // العنوان
        Text(
            text = "🔔 التنبيهات والتذكيرات",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        
        // إعدادات التنبيهات
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("⚙️ إعدادات التنبيهات", fontWeight = FontWeight.Bold)
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("تنبيهات الجلسات")
                    Switch(checked = true, onCheckedChange = {})
                }
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("طلبات الوصول")
                    Switch(checked = true, onCheckedChange = {})
                }
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("تحديثات الحالات")
                    Switch(checked = true, onCheckedChange = {})
                }
            }
        }
        
        // قائمة التنبيهات
        notifications.forEachIndexed { index, notification ->
            NotificationCard(
                notification = notification,
                onDismiss = {
                    notifications = notifications.toMutableList().apply {
                        removeAt(index)
                    }
                },
                onAction = {
                    // إجراء عند النقر
                }
            )
        }
        
        // إذا كانت القائمة فارغة
        if (notifications.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("✅ لا توجد تنبيهات جديدة", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text("أنت متابع بشكل جيد!", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun NotificationCard(
    notification: NotificationItem,
    onDismiss: () -> Unit,
    onAction: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F8FF))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(notification.title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(notification.description, fontSize = 12.sp, color = Color.Gray)
                }
                Text(notification.time, fontSize = 11.sp, color = Color(0xFF999999))
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.height(35.dp)
                ) {
                    Text("تجاهل", fontSize = 12.sp)
                }
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Button(
                    onClick = onAction,
                    modifier = Modifier.height(35.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) {
                    Text("اعرض", fontSize = 12.sp)
                }
            }
        }
    }
}

data class NotificationItem(
    val title: String,
    val description: String,
    val time: String
)