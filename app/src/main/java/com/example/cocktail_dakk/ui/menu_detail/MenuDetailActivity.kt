package com.example.cocktail_dakk.ui.menu_detail

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.cocktail_dakk.R
import com.example.cocktail_dakk.databinding.ActivityMenuDetailBinding
import com.example.cocktail_dakk.ui.BaseActivity
import android.util.TypedValue
import android.view.Gravity
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cocktail_dakk.data.entities.cocktaildata_db.CocktailDatabase
import com.example.cocktail_dakk.data.entities.cocktaildata_db.Cocktail_Rating
import com.example.cocktail_dakk.data.entities.getUser
import com.example.cocktail_dakk.ui.menu_detail.detailService.*
import com.example.cocktail_dakk.utils.getaccesstoken


class MenuDetailActivity : BaseActivity<ActivityMenuDetailBinding>(ActivityMenuDetailBinding::inflate), DetailView,
    RatingView {

    // 단위 리스트. 나중에 다른 곳으로 옮길것
    private val unitList = arrayListOf("ml", "piece", "개", "필업")
    // 레시피 랜덤 색상 리스트. 나중에 다른 곳으로 옮길것
    private val colorList1 = arrayListOf("FF4668", "FCF5A4","03EF9A","A35BBF")
    private val colorList2 = arrayListOf("FF6363", "14D2D2", "208DC8", "C4A5E1")
    val detailService = DetailService()

    private var ingredients : ArrayList<String> = ArrayList()
    private var keywords : ArrayList<String> = ArrayList()
    private val ratios : MutableList<Int> = ArrayList()
    private var colors : List<String> = (colorList1 as MutableList<String>).shuffled() + (colorList2 as MutableList<String>).shuffled()
    private var weights : MutableList<Float> = ArrayList()
    private var starPoint: Double = 0.0
    private var tempStarPoint: Int = -1

    lateinit var localName : String
    lateinit var englishName : String
    lateinit var imageURL : String
    var alcoholLevel : Int = 0
    lateinit var mixxing : String
    lateinit var getkeywords : String
    lateinit var information : String
    lateinit var getingredients : String
    var cocktailInfoId : Int = 0

    override fun initAfterBinding() {
        initClicker()

        //서버에서 가져오기
        cocktailInfoId = intent.getIntExtra("id",0)
        detailService.setdetailView(this)
        detailService.setratingView(this)
        detailService.detail(getaccesstoken(this),cocktailInfoId)

    }

    //디테일 화면로딩
    override fun onDetailLoading() {
    }

    override fun onDetailSuccess(result: detail_Cocktail) {
        localName = result.koreanName
        englishName = result.englishName
        imageURL = result.nukkiImgUrl
        starPoint = result.ratingAvg
        alcoholLevel = result.alcoholLevel
        mixxing = result.cocktailMixingMethod[0].mixingMethodName
        getkeywords = ""
        for(i in 0..result.cocktailKeyword.size-1){
            getkeywords += result.cocktailKeyword[i].keywordName + ","
        }
        information = result.description
        getingredients = result.ingredient
        Glide.with(this)
            .load(result.todayImgUrl)
            .thumbnail(0.1f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.recommend_todays2)
            .into(binding.menuDetailBackgroundIv)
        initCocktail()
        ratingreset()
    }
    fun ratingreset(){

        //기본
        binding.menuDetailStarEvaluateTv.text = "평가 하기"
        binding.menuDetailStarEvaluateTv.setOnClickListener(){
            binding.menuDetailEvaluateBackgroundLa.visibility = View.VISIBLE
            var animation2 : Animation = AlphaAnimation(0f,1f);
            animation2.setDuration(300)
            binding.menuDetailEvaluateBackgroundLa.animation = animation2

//            if (starPoint != 0.0) {
//                clickStar(starPoint)}
//            else {
            binding.menuDetailEvaluateStar1Iv.setImageResource(R.drawable.star_off)
            binding.menuDetailEvaluateStar2Iv.setImageResource(R.drawable.star_off)
            binding.menuDetailEvaluateStar3Iv.setImageResource(R.drawable.star_off)
            binding.menuDetailEvaluateStar4Iv.setImageResource(R.drawable.star_off)
            binding.menuDetailEvaluateStar5Iv.setImageResource(R.drawable.star_off)
            binding.menuDetailEvaluateOkOffTv.visibility = View.VISIBLE
            binding.menuDetailEvaluateOkOnTv.visibility = View.INVISIBLE
//            }
        }

        var CocktailDB = CocktailDatabase.getInstance(this)!!
        val ratinglist = CocktailDB.RatingDao().getcocktails()
        for(i in 0..ratinglist.size-1){
            if (ratinglist[i].cocktailinfoid == cocktailInfoId){
                binding.menuDetailStarEvaluateTv.text = "평가 완료"
                binding.menuDetailStarEvaluateTv.setOnClickListener {
                    Toast.makeText(this,"이미 평가 하셨습니다!",Toast.LENGTH_SHORT).show()
                }
                break
            }
        }

        binding.menuDetailEvaluateOkOnTv.setOnClickListener(){
            var CocktailDB = CocktailDatabase.getInstance(this)!!
            CocktailDB.RatingDao().insert(Cocktail_Rating(cocktailInfoId))
            binding.menuDetailStarEvaluateTv.text = "평가 완료"
            binding.menuDetailStarEvaluateTv.setOnClickListener {
                Toast.makeText(this,"이미 평가 하셨습니다!",Toast.LENGTH_SHORT).show()
            }
//            detailService.rating(
//                DetailRequest(cocktailInfoId,
//                    getUser(this).deviceNum,tempStarPoint)
//            )
            binding.menuDetailEvaluateBackgroundLa.visibility = View.GONE
        }
    }


    override fun onDetailFailure(code: Int, message: String) {
    }

    private fun initClicker(){

        binding.menuDetailBackIv.setOnClickListener(){
            finish()
        }

        binding.menuDetailStarEvaluateTv.setOnClickListener(){
            binding.menuDetailEvaluateBackgroundLa.visibility = View.VISIBLE
            var animation2 : Animation = AlphaAnimation(0f,1f);
            animation2.setDuration(300)
            binding.menuDetailEvaluateBackgroundLa.animation = animation2

//            if (starPoint != 0) {clickStar(starPoint)} else {
                binding.menuDetailEvaluateStar1Iv.setImageResource(R.drawable.star_off)
                binding.menuDetailEvaluateStar2Iv.setImageResource(R.drawable.star_off)
                binding.menuDetailEvaluateStar3Iv.setImageResource(R.drawable.star_off)
                binding.menuDetailEvaluateStar4Iv.setImageResource(R.drawable.star_off)
                binding.menuDetailEvaluateStar5Iv.setImageResource(R.drawable.star_off)
                binding.menuDetailEvaluateOkOffTv.visibility = View.VISIBLE
                binding.menuDetailEvaluateOkOnTv.visibility = View.INVISIBLE
//            }
        }

        // 평가하기
        binding.menuDetailEvaluateWhiteboardLa.setOnClickListener(){
            // 아무것도 안함. 배경 클릭과의 대비를 두기 위한 코드. 지우지 말것!
        }

        binding.menuDetailEvaluateBackgroundLa.setOnClickListener(){
            binding.menuDetailEvaluateBackgroundLa.visibility = View.GONE
        }
        binding.menuDetailEvaluateExitIv.setOnClickListener(){
            binding.menuDetailEvaluateBackgroundLa.visibility = View.GONE
        }

        binding.menuDetailEvaluateStar1Iv.setOnClickListener(){
            tempStarPoint = 1
            clickStar(1.0)
        }
        binding.menuDetailEvaluateStar2Iv.setOnClickListener(){
            tempStarPoint = 2
            clickStar(2.0)
        }
        binding.menuDetailEvaluateStar3Iv.setOnClickListener(){
            tempStarPoint = 3
            clickStar(3.0)
        }
        binding.menuDetailEvaluateStar4Iv.setOnClickListener(){
            tempStarPoint = 4
            clickStar(4.0)
        }
        binding.menuDetailEvaluateStar5Iv.setOnClickListener(){
            tempStarPoint = 5
            clickStar(5.0)
        }

        binding.menuDetailEvaluateOkOffTv.setOnClickListener(){
            Toast.makeText(this, "별점을 평가해 주세요.", Toast.LENGTH_SHORT).show()
        }
        binding.menuDetailEvaluateOkOnTv.setOnClickListener(){
            var CocktailDB = CocktailDatabase.getInstance(this)!!
            CocktailDB.RatingDao().insert(Cocktail_Rating(cocktailInfoId))
            binding.menuDetailStarEvaluateTv.text = "평가 완료"
            binding.menuDetailStarEvaluateTv.setOnClickListener {
                Toast.makeText(this,"이미 평가 하셨습니다!",Toast.LENGTH_SHORT).show()
            }
//            detailService.rating(DetailRequest(cocktailInfoId,getUser(this).deviceNum,tempStarPoint))
            binding.menuDetailEvaluateBackgroundLa.visibility = View.GONE
        }
    }


    private fun initCocktail(){
        // local 이름, english 이름, image 넣기
        binding.menuDetailNameLocalTv.text = localName
        binding.menuDetailNameEnglishTv.text = englishName
        Glide.with(this)
            .load(imageURL)
            .thumbnail(0.1f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.img_cocktail_alaskaicedtea_dailyrec)
            .into(binding.menuDetailBigCocktailIv)

        // 별점 넣기, 도수 넣기
        initStarPoint(
            starPoint,
            binding.menuDetailStarContext1Iv,
            binding.menuDetailStarContext2Iv,
            binding.menuDetailStarContext3Iv,
            binding.menuDetailStarContext4Iv,
            binding.menuDetailStarContext5Iv
        )
        binding.menuDetailAlcoholLevelContextTv.text = alcoholLevel.toString() + " 도"

        // 평가하기 창 클릭시 이름들 넣기
        binding.menuDetailEvaluateNameLocalTv.text =localName
        binding.menuDetailEvaluateNameEnglishTv.text = englishName
        binding.menuDetailEvaluateGuideTv.text = localName+"에 대한 별점을 평가해 주세요."

        // 키워드 넣기
        initKeywords(getkeywords)
        val l1 = binding.menuDetailKeywordsContextFb

        for (i in 0 until keywords.size-1){
            l1.addView(createKeyword(keywords[i], 12.0f, "000000", 60))
            val vu = View(this)
            var layoutparam = LinearLayout.LayoutParams(DPtoPX(this,10), 0)
            layoutparam.setMargins(0,80,0,0)
            vu.layoutParams = layoutparam
            l1.addView(vu)
        }

        // 정보 넣기
        binding.menuDetailCocktailInformationContextTv.text = information

        // 재료와 비율 넣기
        initIngredientsAndRatio(getingredients)
        for (ing in ingredients) {
            binding.menuDetailIngredientsContextLa.addView(createTextView(ing, 14.5f, "000000"))
            binding.menuDetailIngredientsContextLa.addView(createTextView("", 0f,"000000",10,13))
        }

        for (i in 0 until ingredients.size) {
            binding.menuDetailRecipeContextLa.addView(createIngredientWithColor(colors[i], ingredients[i], 13.0f, "FFFFFF"))
            binding.menuDetailRecipeContextLa.addView(createTextView("", 0f,"000000",5,10))
        }

        // 재료 비율 시각화와 색깔지정
        var ratioSum: Int = 0
        var underFourCount: Int = 0
        for (i in 0 until ratios.size) {
            if (ratios[i] > 4) {
            ratioSum += ratios[i]
            }else {
            underFourCount += 1
            }
        }
        for (i in 0 until ratios.size) {
            if (ratios[i]<=4) {
                weights.add(4.0f)
            } else {
                weights.add( ((150 - underFourCount*4) * ratios[i]/ratioSum).toFloat())
            }
        }

        for (i in 0 until ratios.size) {
            binding.menuDetailRecipeRatioLa.addView(createViewWithWeight(colors[i], weights[i]))
            binding.menuDetailRecipeRatioLa.addView(createViewWithHeight(3))
        }
        binding.menuDetailRecipeRatioLa.requestLayout()

    }

    private fun initStarPoint(starPoint: Double, star_1: ImageView, star_2: ImageView, star_3: ImageView, star_4: ImageView, star_5: ImageView){

        // 별점
        // 0.5 단위로 "버림" 연산
        // 예) 5.0 -> 5  //  4.8 -> 4.5  // 4.4 -> 4  // 2.1 -> 2
        // 0.0점~0.99점 까지는 예외적으로 0.5 를 줬음. (하나도 안 채워져 있으면 이상해보여서)

        val starEmpty: Int = R.mipmap.icon_star_off
        val starFull: Int = R.mipmap.icon_star_on
        val starHalf: Int = R.mipmap.icon_star_half

        if (starPoint >= 1.0f) {
            star_1.setImageResource(starFull)
            if (starPoint >= 2.0f) {
                star_2.setImageResource(starFull)
                if (starPoint >= 3.0f) {
                    star_3.setImageResource(starFull)
                    if (starPoint >= 4.0f) {
                        star_4.setImageResource(starFull)
                        if (starPoint >= 5.0f) {
                            star_5.setImageResource(starFull)
                        } else if (starPoint >= 4.5f) {
                            star_5.setImageResource(starHalf)
                        } else {
                            star_5.setImageResource(starEmpty)
                        }
                    } else if (starPoint >= 3.5f) {
                        star_4.setImageResource(starHalf)
                    } else {
                        star_4.setImageResource(starEmpty)
                    }
                } else if (starPoint >= 2.5f) {
                    star_3.setImageResource(starHalf)
                } else {
                    star_3.setImageResource(starEmpty)
                }
            } else if (starPoint >= 1.5f) {
                star_2.setImageResource(starHalf)
            } else {
                star_2.setImageResource(starEmpty)
            }
        } else {  // 0.0점~0.99점 까지는 예외적으로 0.5 를 줬음. (하나도 안 채워져 있으면 이상해보여서)
            star_1.setImageResource(starHalf)
        }
    }

    private fun initIngredientsAndRatio(inputIngredients: String){
        // ingredients
        ingredients = inputIngredients.split(",") as ArrayList<String>
        for (i in 0 until ingredients.size){
            ingredients[i] = ingredients[i].trim()//공백제거
        }
        ingredients.reverse()

        // ratios
        for (ing in ingredients){
            var unitCount = 0
            var unitVal = 0

            while (unitCount < 4){
                val unitIdx = ing.lastIndexOf(unitList[unitCount])
                if (unitIdx == -1){
                    unitCount++
                } else {
                    unitVal = if (unitCount == 3) { // 필업인 경우 고정값
                        70
                    } else { // 단위 앞의 숫자를 unitVal에 찾아 넣기
                        var startIdx = unitIdx-1
                        while (startIdx >=0) {
                            var temp = Character.getNumericValue(ing[startIdx])
                            if (temp == -1) {
                                startIdx++
                                break
                            }

                            startIdx--
                        }
                        ing.substring(startIdx until unitIdx).toInt()
                    }
                    break
                }
            }
            ratios.add(unitVal)
        }

    }

    private fun initKeywords(inputKeywords: String) {
        keywords = inputKeywords.split(",") as ArrayList<String>
        for (i in 0 until keywords.size) {
            keywords[i] = keywords[i].trim()
        }
    }

    private fun createTextView(inputText : String, size: Float, color: String, width: Int = -1, height: Int = -1) :TextView{
        val textView = TextView(this)
        textView.text = inputText
        textView.textSize = size
        textView.setTextColor(Color.parseColor("#$color"))
        val lp =
            if (width==-1 && height==-1) LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            else LinearLayout.LayoutParams(DPtoPX(this, width), DPtoPX(this, height))
        textView.layoutParams = lp
        return textView
    }

    private fun createKeyword(inputText : String, size: Float, color: String, width: Int = -1, height: Int = -1) :TextView{
        val textView = TextView(this)
        textView.text = inputText
        textView.textSize = size
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textView.setBackgroundResource(R.drawable.round_rect_white_in_sky)
        textView.setTextColor(Color.parseColor("#$color"))
        textView.setPadding(0,DPtoPX(this,2),0,DPtoPX(this,2))
        val lp =
            if (width==-1 && height==-1) LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            else if (width != -1) {
                LinearLayout.LayoutParams(DPtoPX(this, width), ViewGroup.LayoutParams.WRAP_CONTENT)
            } else LinearLayout.LayoutParams(DPtoPX(this, width), DPtoPX(this, height))
        textView.layoutParams = lp
        return textView
    }

    private fun DPtoPX(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
    }

    private fun createViewWithWeight(colorText: String, inputWeight: Float) :View{
        val vu = View(this)
        vu.setBackgroundResource(R.drawable.shape_rect_white)
        vu.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#"+colorText))

        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DPtoPX(this, 1)).apply {
            weight = inputWeight
        }
        vu.layoutParams = lp
        return vu
    }

    private fun createViewWithHeight(inputHeight: Int) :View{
        val vu = View(this)
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DPtoPX(this, inputHeight))
        vu.layoutParams = lp
        return vu
    }

    private fun createIngredientWithColor(colorText:String, inputText : String, size: Float, textColor: String, width: Int = -1, height: Int = -1): LinearLayout{
        val la = LinearLayout(this)
        var lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        la.gravity = Gravity.CENTER_VERTICAL or Gravity.LEFT or Gravity.FILL_HORIZONTAL
        la.orientation = LinearLayout.HORIZONTAL
        la.layoutParams = lp

        val vu = View(this)
        vu.layoutParams = LinearLayout.LayoutParams(DPtoPX(this, 18), DPtoPX(this, 18))
        vu.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#"+colorText))
        vu.setBackgroundResource(R.drawable.shape_circle_white)

        val tv = createTextView(inputText, size, textColor, width, height)
        var lp3 = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp3.setMargins(DPtoPX(this, 10),0,0,0)
        tv.layoutParams = lp3
        la.addView(vu)
        la.addView(tv)

        la.requestLayout()

        return la
    }

    private fun clickStar(point: Double){
        val full = R.mipmap.icon_star_on
        val empty = R.mipmap.icon_star_off

        when (point){
            1.0 -> {
                binding.menuDetailEvaluateStar1Iv.setImageResource(full)
                binding.menuDetailEvaluateStar2Iv.setImageResource(empty)
                binding.menuDetailEvaluateStar3Iv.setImageResource(empty)
                binding.menuDetailEvaluateStar4Iv.setImageResource(empty)
                binding.menuDetailEvaluateStar5Iv.setImageResource(empty)
            }
            2.0 -> {
                binding.menuDetailEvaluateStar1Iv.setImageResource(full)
                binding.menuDetailEvaluateStar2Iv.setImageResource(full)
                binding.menuDetailEvaluateStar3Iv.setImageResource(empty)
                binding.menuDetailEvaluateStar4Iv.setImageResource(empty)
                binding.menuDetailEvaluateStar5Iv.setImageResource(empty)
            }
            3.0 -> {
                binding.menuDetailEvaluateStar1Iv.setImageResource(full)
                binding.menuDetailEvaluateStar2Iv.setImageResource(full)
                binding.menuDetailEvaluateStar3Iv.setImageResource(full)
                binding.menuDetailEvaluateStar4Iv.setImageResource(empty)
                binding.menuDetailEvaluateStar5Iv.setImageResource(empty)
            }
            4.0 -> {
                binding.menuDetailEvaluateStar1Iv.setImageResource(full)
                binding.menuDetailEvaluateStar2Iv.setImageResource(full)
                binding.menuDetailEvaluateStar3Iv.setImageResource(full)
                binding.menuDetailEvaluateStar4Iv.setImageResource(full)
                binding.menuDetailEvaluateStar5Iv.setImageResource(empty)
            }
            5.0 -> {
                binding.menuDetailEvaluateStar1Iv.setImageResource(full)
                binding.menuDetailEvaluateStar2Iv.setImageResource(full)
                binding.menuDetailEvaluateStar3Iv.setImageResource(full)
                binding.menuDetailEvaluateStar4Iv.setImageResource(full)
                binding.menuDetailEvaluateStar5Iv.setImageResource(full)
            }
        }

        binding.menuDetailEvaluateOkOffTv.visibility = View.INVISIBLE
        binding.menuDetailEvaluateOkOnTv.visibility = View.VISIBLE
    }

    //레이팅
    override fun onRatingLoading() {
    }

    override fun onRatingSuccess(result: ratingResponse) {
        Toast.makeText(this, "별점 ${tempStarPoint}점을 기록했습니다.", Toast.LENGTH_SHORT).show()

    }

    override fun onRatingFailure(code: Int, message: String) {
        Toast.makeText(this, "별점 등록을 실패했어요!", Toast.LENGTH_SHORT).show()

    }

}