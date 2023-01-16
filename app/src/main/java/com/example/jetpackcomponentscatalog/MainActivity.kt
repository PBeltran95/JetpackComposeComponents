package com.example.jetpackcomponentscatalog

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomponentscatalog.model.Routes
import com.example.jetpackcomponentscatalog.model.Routes.*
import com.example.jetpackcomponentscatalog.ui.theme.JetpackComponentsCatalogTheme

class MainActivity : ComponentActivity() {

    private var show = false
    private val myViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComponentsCatalogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen1.route) {
                        composable(Screen1.route) { Screen1(navController) }
                        composable(Screen2.route) { Screen2(navController) }
                        composable(Screen3.route) { Screen3(navController) }
                        composable("pantalla4/{name}", arguments = listOf(navArgument("name") {
                            type = NavType.IntType
                        })) { backStackEntry ->
                            Screen4(
                                navController,
                                backStackEntry.arguments?.getInt("name") ?: 0
                            )
                        }
                    }
                }
            }
        }
    }
}


    private fun showToast(context: Context) {
        Toast.makeText(context, "This is a Toast", Toast.LENGTH_SHORT).show()
    }


@Composable
fun MyDropDownMenu() {
    var selectedText by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val desserts = listOf("Helado", "Chocolate", "Fruta", "Postre")
    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach { dessert -> DropdownMenuItem(onClick = {
                expanded = false
                selectedText = dessert
            }) {
                Text(text = dessert)
            } }
        }
    }
}

@Composable
fun MyDivider() {
    Divider(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp))
}

@Composable
fun MyBadgeBox() {
    BadgedBox(
        modifier = Modifier.padding(20.dp),
        badge = {
            Badge(
                content = {
                    Text(modifier = Modifier.padding(2.dp),text = "10")
                },
                backgroundColor = Color.Black,
                contentColor = Color.White,
                modifier = Modifier.padding(2.dp)
            )
        },
    ){
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.Default.Star,
            contentDescription = "l",
            tint = Color.Blue
        )
    }
}

@Composable
fun MySecondBadgeBox() {
    Box(modifier = Modifier.padding(16.dp)
    ) {
        BadgedBox( badge = { Badge { Text(text = "1") } }
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun MyCard() {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        elevation = 12.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.Magenta,
        contentColor = Color.White
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = "Hola")
            Text(text = "Hola")
            Text(text = "Hola")
        }
    }
}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .clickable {
                onItemSelected("Radio Button 1")
            }
            .fillMaxWidth()) {
            RadioButton(
                selected = name == "Radio Button 1",
                onClick = { onItemSelected("Radio Button 1") },
                enabled = true,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Red,
                    unselectedColor = Color.Blue,
                    disabledColor = Color.Yellow
                )
            )
            Spacer(modifier = Modifier
                .width(8.dp)
                .height(0.dp))
            Text(text = "Radio Button 1")
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .clickable {
                onItemSelected("Radio Button 2")
            }
            .fillMaxWidth()) {
            RadioButton(
                selected = name == "Radio Button 2",
                onClick = { onItemSelected("Radio Button 2") },
                enabled = true,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Red,
                    unselectedColor = Color.Blue,
                    disabledColor = Color.Yellow
                )
            )

            Spacer(
                modifier = Modifier
                    .width(8.dp)
                    .height(0.dp)
            )
            Text(text = "Radio Button 2")
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .clickable {
                onItemSelected("Radio Button 3")
            }
            .fillMaxWidth()) {
            RadioButton(
                selected = name == "Radio Button 3",
                onClick = { onItemSelected("Radio Button 3") },
                enabled = true,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Red,
                    unselectedColor = Color.Blue,
                    disabledColor = Color.Yellow
                )
            )

            Spacer(
                modifier = Modifier
                    .width(8.dp)
                    .height(0.dp)
            )
            Text(text = "Radio Button 3")
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .clickable {
                onItemSelected("Radio Button 4")
            }
            .fillMaxWidth()) {
            RadioButton(
                selected = name == "Radio Button 4",
                onClick = { onItemSelected("Radio Button 4") },
                enabled = true,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Red,
                    unselectedColor = Color.Blue,
                    disabledColor = Color.Yellow
                )
            )

            Spacer(
                modifier = Modifier
                    .width(8.dp)
                    .height(0.dp)
            )
            Text(text = "Radio Button 4")
        }
    }
}

@Composable
fun MyRadioButton() {
    var status by rememberSaveable { mutableStateOf(false) }
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

        RadioButton(
            selected = status,
            onClick = { status = !status },
            enabled = true,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Blue,
                disabledColor = Color.Yellow
            )
        )

        Spacer(
            modifier = Modifier
                .width(8.dp)
                .height(0.dp)
        )
        Text(text = "Radio Button")
    }
}

@Composable
fun MyTriStatusCheckBox() {
    var status by rememberSaveable { mutableStateOf(ToggleableState.Off) }
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        TriStateCheckbox(state = status, onClick = {
            status = when(status) {
                ToggleableState.On -> ToggleableState.Off
                ToggleableState.Off -> ToggleableState.Indeterminate
                ToggleableState.Indeterminate -> ToggleableState.On
            }
        })
        Spacer(
            modifier = Modifier
                .width(8.dp)
                .height(0.dp)
        )
        Text(text = "TriStatus checkBox")
    }
}


@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title = it,
            selected = status,
            onCheckedChange = { myNewStatus -> status = myNewStatus }
        )
    }
}


@Composable
fun MyAdvancedCheckBoxCompleted(checkInfo: CheckInfo) {
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        Spacer(
            modifier = Modifier
                .width(8.dp)
                .height(0.dp)
        )
        Text(text = checkInfo.title)
    }
}



@Composable
fun MyAdvancedCheckBox() {
    var checkBoxState by rememberSaveable { mutableStateOf(false) }
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checkBoxState, onCheckedChange = { checkBoxState = !checkBoxState })
        Spacer(modifier = Modifier
            .width(8.dp)
            .height(0.dp))
        Text(text = "Deseas checkear la checkBox?")
    }
}

@Composable
fun MyCheckBox() {
    var checkBoxState by rememberSaveable { mutableStateOf(false) }
    Checkbox(
        checked = checkBoxState,
        onCheckedChange = { checkBoxState = !checkBoxState },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            checkmarkColor = Color.Black
        )
    )
}

@Composable
fun MySwitch() {
    var switchState by rememberSaveable { mutableStateOf(false) }
    Switch(
        checked = switchState,
        onCheckedChange = { switchState = !switchState },
        enabled = true,
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            checkedThumbColor = Color.Green,
            uncheckedTrackColor = Color.Red,
            uncheckedTrackAlpha = 0.5f
        )
    )
}

@Composable
fun MySuperAdvancedProgress() {
    var progress by rememberSaveable { mutableStateOf(0.1f) }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(
            modifier = Modifier.padding(top = 16.dp),
            progress = progress
        )
        Row(Modifier.fillMaxWidth()) {
            Button(onClick = { if (progress < 1f) progress += 0.1f },
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(1f)) {
                Text(text = "Incrementar")
            }
            Button(onClick = { if (progress > 0f) progress -= 0.1f },
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(1f)) {
                Text(text = "Reducir")
            }
        }
    }
}

@Composable
fun MyAdvancedProgressBar(show: Boolean) {

    val showLoading by rememberSaveable { mutableStateOf(show) }

    if (showLoading) {
        CircularProgressIndicator(
            modifier = Modifier.padding(top = 16.dp),
            color = Color.Red,
            strokeWidth = 2.dp
        )
        LinearProgressIndicator(
            modifier = Modifier
                .padding(top = 16.dp)
                .height(10.dp), color = Color.Red, backgroundColor = Color.Yellow
        )
    }
}

@Composable
fun MyProgressBar() {
    CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp), color = Color.Red, strokeWidth = 2.dp)
    LinearProgressIndicator(modifier = Modifier
        .padding(top = 16.dp)
        .height(10.dp), color = Color.Red, backgroundColor = Color.Yellow)
}

@Composable
fun MyIcon() {
    Icon(
        imageVector = Icons.Rounded.Star,
        tint = Color.Red,
        contentDescription = "Example",
        modifier = Modifier
            .background(shape = CircleShape, color = Color.Transparent)
            .padding(top = 16.dp)
            .clickable { })
}

@Composable
fun MyImageAdvanced() {

    Image(painter = painterResource(
        id = R.drawable.ic_launcher_background),
        contentDescription = "Ejemplo",
        modifier = Modifier
            .padding(top = 16.dp)
            .clip(CircleShape)
            .border(width = 2.dp, shape = CircleShape, color = Color.Black)
    )
}

@Composable
fun MyImage() {
    Image(painter = painterResource(
        id = R.drawable.ic_launcher_background),
        contentDescription = "Ejemplo",
        modifier = Modifier
            .padding(top = 16.dp)
            .alpha(0.5f)
    )
}

@Composable
fun MyTextButton() {
    TextButton(onClick = { }, modifier = Modifier.padding(top = 16.dp)) {
        Text(text = "Hola soy un textButton")
    }
}

@Composable
fun MyButtonExample() {
    var enabled by rememberSaveable { mutableStateOf(true) }

    Button(
        onClick = { enabled = !enabled },
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue,
            contentColor = Color.White
        ),
        border = BorderStroke(2.dp, color = Color.Green)

        ) {
        Text(text = "Hola")
    }
}

@Composable
fun MyOutlinedButtonExample(action: () -> Unit) {

    OutlinedButton(
        modifier = Modifier.padding(top = 16.dp),
        onClick = { action() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue,
            contentColor = Color.White
        ),
        ) {
        Text(text = "Hola soy un boton outlined")
    }
}

@Composable
fun MyTextFieldOutlined(myText: String, action: (String) -> Unit) {
    OutlinedTextField(value = myText, onValueChange = {
        action(it)
    }, label = { Text(text = "Introduce tu nombre", textAlign = TextAlign.End) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Blue
        ),
        shape = RoundedCornerShape(30.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Campo de texto del icono") }
    )
}

@Composable
fun MyTextFieldAdvanced() {
    var myText by remember { mutableStateOf("") }
    TextField(value = myText, onValueChange = {
        myText = if (it.contains("a")) it.replace("a", "") else it
    }, label = { Text(text = "Introduce tu nombre") })
}

@Composable
fun MyTextField() {
    var myText by remember { mutableStateOf("") }
    TextField(value = myText, onValueChange = { myText = it })
}

@Composable
fun MyText() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(text = "Lorem impsum de prueba")
        Text(text = "Lorem impsum de prueba", color = Color.Cyan)
        Text(text = "Lorem impsum de prueba", fontWeight = FontWeight.ExtraBold)
        Text(text = "Lorem impsum de prueba", fontWeight = FontWeight.Light)
        Text(text = "Lorem impsum de prueba", fontFamily = FontFamily.Cursive)
        Text(
            text = "Lorem impsum de prueba",
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(
            text = "Lorem impsum de prueba",
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Text(
            text = "Lorem impsum de prueba", style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(TextDecoration.LineThrough, TextDecoration.Underline)
                )
            )
        )

        Text(text = "Lorem impsum de prueba", fontSize = 30.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComponentsCatalogTheme {
        var myText by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyTextFieldOutlined(myText) { myText = it }

            Spacer(modifier = Modifier
                .height(16.dp)
                .fillMaxWidth())

            MyButtonExample()

            var showLoading by rememberSaveable { mutableStateOf(false) }
            MyOutlinedButtonExample() { showLoading = !showLoading }

            MyTextButton()
            MyImage()
            MyImageAdvanced()
            MyIcon()
            MyProgressBar()
            MyAdvancedProgressBar(false)
            MySuperAdvancedProgress()
            MySwitch()
            MyCheckBox()
            MyAdvancedCheckBox()
            MyCard()
            MyBadgeBox()
            MySecondBadgeBox()
            MyDivider()
            MyDropDownMenu()
            BasicSlider()
            MyRangeSlider()
        }
    }
}