package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput:TextView?=null
    var lastNumeric : Boolean = false
    var lastDot : Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput=findViewById(R.id.tvInput)
    }
    fun onDigit(view:View){
         Toast.makeText(this,"Button Clicked",Toast.LENGTH_LONG).show()
         lastNumeric = true
         lastDot=false
    }
    fun onClear(view: View){
        tvInput?.text=" "
    }
    fun onDecimalPoint(view: View){
         if(lastNumeric && !lastDot){
           tvInput?.append(".")
             lastNumeric=false
             lastDot=true
         }
    }
    fun onOperator(view: View){
        tvInput?.text?.let{
        if(lastNumeric && !isOperatorAdded(it.toString())){
            tvInput?.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
        }
    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue=tvInput?.text.toString()
            var prefix=" "
            try{
               if(tvValue.startsWith("-")){
                   prefix="-"
                   tvValue=tvValue.substring(1)
               }
                if(tvValue.contains("-")){


                val splitValue=tvValue.split(delimiters = "-")
                var one=splitValue[0]
                var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
               // var result=one.toDouble()-two.toDouble()
                tvInput?.text=removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())}
                else if(tvValue.contains("+")){


                    val splitValue=tvValue.split(delimiters = "+")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    // var result=one.toDouble()-two.toDouble()
                    tvInput?.text=removeZeroAfterDot((one.toDouble()+two.toDouble()).toString())}
                else if(tvValue.contains("/")){


                    val splitValue=tvValue.split(delimiters = "/")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    // var result=one.toDouble()-two.toDouble()
                    tvInput?.text=removeZeroAfterDot((one.toDouble()/two.toDouble()).toString())}
                if(tvValue.contains("*")){


                    val splitValue=tvValue.split(delimiters = "*")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    // var result=one.toDouble()-two.toDouble()
                    tvInput?.text=removeZeroAfterDot((one.toDouble()*two.toDouble()).toString())}

            }catch(e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun removeZeroAfterDot(String):String{
        var value=result
        if(result.contains(other = ".0"))
            value=result.substring(0,result.length-2)
    }
    private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")|| value.contains("*")|| value.contains("+")|| value.contains("-")
        }
    }
}