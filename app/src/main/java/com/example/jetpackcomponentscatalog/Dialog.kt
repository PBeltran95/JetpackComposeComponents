package com.example.jetpackcomponentscatalog

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun MyConfirmationDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Card(shape = MaterialTheme.shapes.small) {
                Column(
                    Modifier
                        .background(Color.White)
                        .width(300.dp)
                ) {
                    MyTitleDialog("Phone ringtone")
                    Divider(Modifier.fillMaxWidth())
                    var status by remember { mutableStateOf("") }
                    MyRadioButtonList(name = status, onItemSelected = { status = it } )
                    Divider(Modifier.fillMaxWidth())
                    Row(modifier = Modifier
                        .align(Alignment.End)
                        .padding(4.dp)) {
                        TextButton(onClick = { onDismiss() }) {
                            Text(text = "CANCEL")
                        }
                        TextButton(onClick = { onConfirm() }) {
                            Text(text = "OK")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyCustomDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .width(400.dp)
                    .height(250.dp)
            ) {
                MyTitleDialog("Set backup account")
                AccountItem("prueba1@gmail.com", R.drawable.ic_launcher_background)
                AccountItem("prueba2@gmail.com", R.drawable.ic_launcher_background)
                AccountItem("Agregar nueva cuenta", R.drawable.ic_baseline_add_circle_24)
            }
        }
    }
}

@Composable
fun MySimpleDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Card(
                Modifier
                    .padding(24.dp)
                    .width(300.dp)
                    .height(150.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Esto es un ejemplo", modifier = Modifier.padding(top = 16.dp))
                    Spacer(modifier = Modifier.padding(16.dp))
                    Row {
                        Button(
                            onClick = { onConfirm() },
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .weight(1f)
                        ) { Text(text = "Confirmar") }
                        Button(
                            onClick = { onDismiss() },
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .weight(1f)
                        ) { Text(text = "Salir") }
                    }
                }
            }
        }
    }
}

@Composable
fun MyDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Titulo") },
            text = { Text(text = "Hola soy una descripcion") },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}

@Composable
fun MyTitleDialog(text: String) {
    Text(text = text,
        fontWeight = FontWeight.W500,
        fontSize = 19.sp,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun AccountItem(email: String, @DrawableRes drawable: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )

        Text(
            text = email,
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(8.dp)
        )
    }
}
