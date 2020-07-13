package world.shanya.serialportutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import world.shanya.serialportutils.SerialPort

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serialPort = SerialPort.getInstance(this)

        serialPort.dataType = SerialPort.DATA_HEX

        serialPort.getReadData(object :SerialPort.ReadDataCallback{
            override fun readData(data: String) {
                MainScope().launch {

                    println(data)

                }
            }
        })

        serialPort.sendDataDownString = "down\r\n"
        serialPort.switchOnText = "on\r\n"
        serialPort.switchOffText = "off\r\n"

        button.setOnClickListener {
            serialPort.sendData("asd\r\n")
        }

        button2.setOnClickListener {
            serialPort.openSearchPage(this)
        }

        button3.setOnTouchListener(serialPort.sendDataButtonListener)
        button4.setOnClickListener(serialPort.sendDataSwitchListener)
    }
}