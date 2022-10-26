package ru.vi.myjetpack

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.vi.myjetpack.databases.History
import ru.vi.myjetpack.databases.HistoryDatabase
import ru.vi.myjetpack.databases.HistoryRepository

import java.lang.Exception
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*


class MainViewModel(application: Application) : ViewModel() {


    var text1 by mutableStateOf("")
    var text2 by mutableStateOf("")
    var operation by mutableStateOf("")

    var ch1 by mutableStateOf("")
    var op by mutableStateOf("")

    var result by mutableStateOf("")
    var isfirst = true


    val historydb = HistoryDatabase.invoke(application)


    val allHistory: LiveData<List<History>>
     val historyRepository: HistoryRepository


    init {
        historyRepository = HistoryRepository(HistoryDatabase.getInstance(application).historyDao())
        allHistory = historyRepository.allHistory

    }



    fun bClick(str:String)
    {

        when(str) {
            "1","2","3","4","5","6","7","8","9","0" -> {
                if (ch1.isNotBlank()){
                    ch1=""
                    text1 = ""
                    text2 = ""
                    operation = ""
                }
            if (text1=="0")
                    text1=str
                else  text1+=str

            }


        "+","-","*","/" -> {

                if (operation.isNotBlank()&&text2.isNotBlank()&&text1.isNotBlank()) {

                    if (operation=="=")
                    {
                        text2=text1.toBigDecimal().stripTrailingZeros().toPlainString()
                        operation=str
                        ch1=""
                        text1 = ""
                    }
                    else {

                        var t1 = text1
                        text2 = calc(text1, text2, operation)

                        text1 = ""
                        operation = str
                    }

                }
                if (text2.isNotBlank()) operation = str
                else {
                    operation = str
                    text2 = text1.toBigDecimal().stripTrailingZeros().toPlainString()
                    text1 = ""

                }


        }

            "C" ->{
                text1=""
                text2=""
                operation=""
                ch1=""
                op=""
            }
            "." ->{
                if (ch1.isNotBlank()){
                    ch1=""
                    text1 = ""
                    text2 = ""
                    operation = ""
                }
                if (text1==""){
                    text1="0."

                }
                if (!text1.contains("."))
                    text1+=str
                //text1=text1.toBigDecimal().toString()
            }
            "<-" -> {
                if (text1.isNotBlank() && text1.isNotEmpty()) {
                    text1 = text1.substring(0, text1.length - 1);
            }}
            "=" -> {
                if(text2.isBlank()) return
                if (ch1.isBlank()) {
                    if(text1.isBlank()) ch1=text2
                    else {
                        ch1 = text1.toBigDecimal().stripTrailingZeros().toPlainString()
                    }
                    op = operation

                }
                else
                {
                    text1 = calc(ch1, text1, op)
                    text2 = "$text2 $op $ch1"
                    historyRepository.insertRecord( History("$text2 $operation $text1", getTime()) )
                    return
                }

                if (text1.isBlank()) text1=text2
                var t1 = text1.toBigDecimal().stripTrailingZeros().toPlainString()
                text1 = calc(t1, text2, operation)

                text2 = "$text2 $op $t1"
                operation = "="
                historyRepository.insertRecord( History("$text2 $operation $text1", getTime()) )

            }
            "ClHist" ->
            {
                historyRepository.deleteAll()
            }
            "Hist" -> {
                
            }
        }

    }

    fun calc (t1:String,t2:String,op:String):String
    {   var bdt1=BigDecimal(0)
        try {bdt1= t1.toBigDecimal()}
        catch (e:Exception){}
        var bdt2=BigDecimal(0)
        try {bdt2= t2.toBigDecimal()}
        catch (e:Exception){}
        when (op)
        {
            "+" -> return bdt1.plus(bdt2).toString()
            "-" -> return bdt2.minus(bdt1).toString()
            "*" -> return bdt1.multiply(bdt2).toString()
            "/" -> {
                    if (t1 != "0"&&t1.isNotBlank())
                        return bdt2.divide(bdt1,10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString()
                   }
        }

        return "0"
    }


    private fun getTime(): String {
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
        return formatter.format(date)
    }
}