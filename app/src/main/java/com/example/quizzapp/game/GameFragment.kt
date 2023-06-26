package com.example.quizzapp.game

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quizzapp.Constants
import com.example.quizzapp.Question
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentGameBinding

class GameFragment : Fragment(), View.OnClickListener {


    lateinit var binding: FragmentGameBinding

    lateinit var mQuestionList : ArrayList<Question> // mang cau hoi
    private var mSelectedPosition :Int =0            // chon cau hoi
    private var mCorrectAnswer: Int   =0             // cau tra loi dung
    private var mCurrentPosition: Int =1            //vi tri hien tai





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_game, container, false)



        //Tao 1 danh sach mang
        mQuestionList = Constants.getQuestions()


        //b3: Cac textview,button có click xử lý
        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)






        setQuestion() //dat cau hoi





        return binding.root
    }

    //BUOC 1 : TAO HAM CAU HOI
    private fun setQuestion(){

        val question: Question = mQuestionList[mCurrentPosition-1]  //danh sach mang bat dau tu 0, Tong cong co 10 phan tu
                                                                    // id bat dau tu 1 nen phai lay vi tri hien tai-1
        binding.tvQuestion.text =question.question
        binding.imageView.setImageResource(question.image)
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour

        binding.pb.progress = mCurrentPosition  //thanh cong cu progress
        binding.tvProgress.text = "$mCurrentPosition" + "/" + binding.pb.max //hien thi thanh tien trinh


        defaultAppearance()        // cái này đặt lại giao diện mỗi khi có câu hỏi mới
//
        //BUOC 6: THIET LAP LAI TEXT TREN BUTTON
        if(mCurrentPosition == mQuestionList.size)  //neu vi tri hien tai = mang cau hoi cuoi cung
        {
            binding.btnSubmit.text = "Quiz Finished"
        }
        else{

            binding.btnSubmit.text ="Submit"
        }



    }

   //BUOC 2 : TAO HAM CAC CAU TRA LOI CO MAU MAC DINH
    private fun defaultAppearance(){


        val options = ArrayList<TextView>()

        options.add(0,binding.tvOptionOne)
        options.add(1,binding.tvOptionTwo)
        options.add(2,binding.tvOptionThree)
        options.add(3,binding.tvOptionFour)


        for(option in options){


            option.setTextColor(Color.parseColor("#7A8089"))
            //cau hoi mac dinh
            option.typeface = Typeface.DEFAULT
            option.background = context?.let { ContextCompat.getDrawable(it,R.drawable.default_option_border_bg) }
        }



    }
    override fun onClick(v: View?) {

        when(v?.id) //dau ? co nghia ko null

        {
            ///khi nhan click vao cac textview option hoac button submit
            R.id.tv_optionOne ->{

                selectOptionView(binding.tvOptionOne,1)
            }
            R.id.tv_optionTwo ->{

                selectOptionView(binding.tvOptionTwo,2)
            }
            R.id.tv_optionThree ->{

                selectOptionView(binding.tvOptionThree,3)
            }
            R.id.tv_optionFour ->{

                selectOptionView(binding.tvOptionFour,4)
            }
            R.id.btnSubmit ->{
                //neu nguoi dung chua chon bat ki lua chon nao va nhan click btn
                if(mSelectedPosition ==0 ){

                    mCurrentPosition++ //sang cau hoi tiep theo

                    when {
                        mCurrentPosition <= mQuestionList.size -> {

                            setQuestion()

                        }else ->{


                        // go to result-// đi đến kết quả

                        val action = GameFragmentDirections.actionGameFragmentToScoreFragment()
                        val nameOfPlayer by navArgs<GameFragmentArgs>()
                        action.score = mCorrectAnswer
                        action.name = nameOfPlayer.name
                        findNavController().navigate(action)



                            //da den cau hoi cuoi cung
                            Toast.makeText(context,"Quiz Finished",Toast.LENGTH_SHORT).show()
                        }
                    }
                } else{ //nguoc lai neu chon dap an va nhan click btn


                    val question: Question? = mQuestionList[mCurrentPosition-1]

                    // nếu người dùng chọn một tùy chọn
                    // chúng ta sẽ kiểm tra xem nó đúng hay sai
                    if(question!!.correctAnswer != mSelectedPosition){
                        answerView(mSelectedPosition, R.drawable.wrong_option_border_bg)
                    } else {mCorrectAnswer++}

                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionList.size){
                        binding.btnSubmit.text = "FINISH"
                    } else {binding.btnSubmit.text = "Go to next Question"}

                    mSelectedPosition = 0 // next question options positions should be 0


                }
            }
        }

    }

    //BUOC 3 : TAO HAM LUA CHON DAP AN
    private fun selectOptionView(tv: TextView, selectedPosition: Int) {

        //reset tex views
        defaultAppearance()

        mSelectedPosition = selectedPosition


        tv.setTextColor(Color.parseColor("#363A43"))

        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = context?.let { ContextCompat.getDrawable(it,R.drawable.selected_option_border_bg) }


    }

    private fun answerView(mSelectedPosition: Int, drawableView: Int) {
        when (mSelectedPosition){

            1 -> {binding.tvOptionOne.background =
                context?.let { ContextCompat.getDrawable(it,drawableView) }
            }

            2 -> {binding.tvOptionTwo.background =
                context?.let { ContextCompat.getDrawable(it,drawableView) }}


            3 -> {binding.tvOptionThree.background =
                context?.let { ContextCompat.getDrawable(it,drawableView) }}


            4 -> {binding.tvOptionFour.background =
                context?.let { ContextCompat.getDrawable(it,drawableView) }}


        }

    }


}