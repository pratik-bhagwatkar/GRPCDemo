package com.example.grpcdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.grpc.ManagedChannelBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edt_first_num=findViewById<EditText>(R.id.edt_first_num)
        val edt_second_num=findViewById<EditText>(R.id.edt_second_num)
        val btn_submit=findViewById<Button>(R.id.btn_submit)
        val tv_result=findViewById<TextView>(R.id.tv_result)
        var result=0

        var managedChannel = ManagedChannelBuilder.forAddress("",111).build()

        btn_submit.setOnClickListener {

            val first=edt_first_num.text.toString().toInt()
            val second=edt_second_num.text.toString().toInt()

            var stub =CalcGrpc.newBlockingStub(managedChannel)
            var addRequest=CalcProto.AddRequest.newBuilder().setFirstNum(first).setSecondNum(second).build()
            var calc=stub.add(addRequest)
            result=calc.sumResult

        }

        tv_result.text="Addition : $result"


    }
}