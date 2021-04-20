package ru.garindev.rockpaperscissorslizardspock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var rockBtn: View
    lateinit var scissorsBtn: View
    lateinit var paperBtn: View
    lateinit var lizardBtn: View
    lateinit var spockBtn: View
    lateinit var repeatBtn: View
    lateinit var icUserChoice: ImageView
    lateinit var icCompChoice: ImageView
    lateinit var textResult: TextView
    lateinit var textTotalWins: TextView
    lateinit var textTotalDraws: TextView
    lateinit var textTotalLose: TextView
    lateinit var textCombo: TextView
    var totalWins = 0
    var totalDraws = 0
    var totalLose = 0
    var combo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializer()
    }

    fun initializer(){
        rockBtn = findViewById(R.id.rock_Btn)
        scissorsBtn = findViewById(R.id.scissors_btn)
        paperBtn = findViewById(R.id.paper_Btn)
        lizardBtn = findViewById(R.id.lizard_Btn)
        spockBtn = findViewById(R.id.spock_Btn)
        repeatBtn = findViewById(R.id.repeat_Btn)
        icUserChoice = findViewById(R.id.user_Choice)
        icCompChoice = findViewById(R.id.comp_Choice)
        textResult = findViewById(R.id.result)
        textTotalWins = findViewById(R.id.totalWins_tv)
        textTotalDraws = findViewById(R.id.totalDraw_tv)
        textTotalLose = findViewById(R.id.totalLose_tv)
        setTotals()
        textCombo = findViewById(R.id.combo_tv)
    }

    fun getUserChoice(view: View){

        when(view.id){
            R.id.rock_Btn -> getWinner("Rock", getCompChoice())
            R.id.scissors_btn -> getWinner("Scissors", getCompChoice())
            R.id.paper_Btn -> getWinner("Paper", getCompChoice())
            R.id.lizard_Btn -> getWinner("Lizard", getCompChoice())
            R.id.spock_Btn -> getWinner("Spock", getCompChoice())
        }
    }

    fun getCompChoice(): String{
        val choiceVariants = arrayOf("Rock", "Scissors", "Paper", "Lizard", "Spock")
        val randomNum = Random.nextInt(0, 4)
        return choiceVariants[randomNum]
    }

    fun getWinner(userChoice: String, compChoice: String) {
        blockUserChoice()
        if (userChoice != compChoice) {
            when (userChoice) {
                "Rock" -> {
                    if (compChoice == "Lizard" || compChoice == "Scissors")
                        setResults(userChoice, compChoice, getString(R.string.win))
                    else
                        setResults(userChoice, compChoice, getString(R.string.lose))

                }
                "Scissors" -> {
                    if (compChoice == "Paper" || compChoice == "Lizard")
                        setResults(userChoice, compChoice, getString(R.string.win))
                    else
                        setResults(userChoice, compChoice, getString(R.string.lose))
                }
                "Paper" -> {
                    if (compChoice == "Rock" || compChoice == "Spock")
                        setResults(userChoice, compChoice, getString(R.string.win))
                    else
                        setResults(userChoice, compChoice, getString(R.string.lose))
                }
                "Lizard" -> {
                    if (compChoice == "Spock" || compChoice == "Paper")
                        setResults(userChoice, compChoice, getString(R.string.win))
                    else
                        setResults(userChoice, compChoice, getString(R.string.lose))
                }
                "Spock" -> {
                    if (compChoice == "Scissors" || compChoice == "Rock")
                        setResults(userChoice, compChoice, getString(R.string.win))
                    else
                        setResults(userChoice, compChoice, getString(R.string.lose))
                }
            }
        } else
            setResults(userChoice, compChoice, getString(R.string.draw))
    }

    fun setResults(userChoice: String, compChoice: String, result: String){
        setImages(icUserChoice, userChoice)
        setImages(icCompChoice, compChoice)
        textResult.text = result
        when(result){
            getString(R.string.win) -> {
                totalWins++
                combo++
                showCombo(combo)
                textTotalWins.text = getString(R.string.total_wins, totalWins)
            }
            getString(R.string.lose) -> {
                totalLose++
                combo = 0
                unShowCombo()
                textTotalLose.text = getString(R.string.total_lose, totalLose)
            }
            getString(R.string.draw) -> {
                totalDraws++
                textTotalDraws.text = getString(R.string.total_draws, totalDraws)
            }
        }
    }

    fun setImages(imageView: ImageView, choice: String){
        when(choice){
            "Rock" -> imageView.setImageResource(R.drawable.ic_rock)
            "Scissors" -> imageView.setImageResource(R.drawable.ic_scissors)
            "Paper" -> imageView.setImageResource(R.drawable.ic_paper)
            "Lizard" -> imageView.setImageResource(R.drawable.ic_lizard)
            "Spock" -> imageView.setImageResource(R.drawable.ic_spock)
        }
    }

    fun blockUserChoice(){
        rockBtn.visibility = View.GONE
        scissorsBtn.visibility = View.GONE
        paperBtn.visibility = View.GONE
        lizardBtn.visibility = View.GONE
        spockBtn.visibility = View.GONE
        repeatBtn.visibility = View.VISIBLE
        icUserChoice.visibility = View.VISIBLE
        icCompChoice.visibility = View.VISIBLE
        textResult.visibility = View.VISIBLE
    }

    fun unBlockUserChoice(view: View){
        repeatBtn.visibility = View.GONE
        icUserChoice.visibility = View.GONE
        icCompChoice.visibility = View.GONE
        textResult.visibility = View.GONE
        rockBtn.visibility = View.VISIBLE
        scissorsBtn.visibility = View.VISIBLE
        paperBtn.visibility = View.VISIBLE
        lizardBtn.visibility = View.VISIBLE
        spockBtn.visibility = View.VISIBLE
    }

    fun onHelpClicked(view: View){
        val intent = Intent(this, HelpActivity::class.java)
        startActivity(intent)
    }

    fun setTotals(){
        textTotalWins.text = resources.getString(R.string.total_wins, totalWins)
        textTotalDraws.text = resources.getString(R.string.total_draws, totalDraws)
        textTotalLose.text = resources.getString(R.string.total_lose, totalLose)
    }

    fun showCombo(i: Int){
        if (i > 1){
            textCombo.text = getString(R.string.combo, i)
            textCombo.visibility = View.VISIBLE
        }
    }

    fun unShowCombo(){
        textCombo.visibility = View.GONE
    }
}