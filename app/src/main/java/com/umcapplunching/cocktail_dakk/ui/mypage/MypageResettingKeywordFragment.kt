package com.umcapplunching.cocktail_dakk.ui.mypage

import android.util.Log
import android.widget.CompoundButton
import com.umcapplunching.cocktail_dakk.R
import com.umcapplunching.cocktail_dakk.databinding.FragmentMypageResettingKeywordBinding
import com.umcapplunching.cocktail_dakk.ui.BaseFragment
import com.umcapplunching.cocktail_dakk.ui.main.MainActivity

class MypageResettingKeywordFragment:BaseFragment<FragmentMypageResettingKeywordBinding>(FragmentMypageResettingKeywordBinding::inflate) {

    private var favorkeyword = ArrayList<String>()
    private var keywordstr = ""
    
    override fun initAfterBinding() {
        SetkeywordListener()
        (activity as MainActivity).setMypageTempKeywords(favorkeyword)
    }

    override fun onStart() {
        favorkeyword.addAll((activity as MainActivity).getMypageKeywords())
        initSelected(favorkeyword)
        super.onStart()
        Log.d("lifeMy_3", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifeMy_3", "onResume")
    }

    override fun onPause() {
        Log.d("lifeMy_3", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("lifeMy_3", "onStop")
        super.onStop()
    }


//    // 크기 다시 조절해주기
//    override fun onResume() {
//        super.onResume()
//        binding.root.requestLayout()
//    }

    private fun SetkeywordListener() {
        val favorListner = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                when (buttonView.id) {
                    R.id.mypage_resetting_keyword_ladykiller_bt -> {
                        favorkeyword.add("레이디킬러")
                        binding.mypageResettingKeywordLadykillerBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_shooter_bt -> {
                        favorkeyword.add("슈터")
                        binding.mypageResettingKeywordShooterBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_clean_bt -> {
                        favorkeyword.add("깔끔한")
                        binding.mypageResettingKeywordCleanBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_tansan_bt -> {
                        favorkeyword.add("탄산")
                        binding.mypageResettingKeywordTansanBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_layered_bt -> {
                        favorkeyword.add("레이어드")
                        binding.mypageResettingKeywordLayeredBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_martini_bt -> {
                        favorkeyword.add("마티니")
                        binding.mypageResettingKeywordMartiniBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_pretty_bt -> {
                        favorkeyword.add("예쁜")
                        binding.mypageResettingKeywordPrettyBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_highball_bt -> {
                        favorkeyword.add("하이볼")
                        binding.mypageResettingKeywordHighballBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_sweet_bt -> {
                        favorkeyword.add("달달한")
                        binding.mypageResettingKeywordSweetBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_dockhan_bt -> {
                        favorkeyword.add("독한")
                        binding.mypageResettingKeywordDockhanBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_sangque_bt -> {
                        favorkeyword.add("상쾌한")
                        binding.mypageResettingKeywordSangqueBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_fluitfavor_bt -> {
                        favorkeyword.add("과일향")
                        binding.mypageResettingKeywordFluitfavorBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_ssupssup_bt -> {
                        favorkeyword.add("씁쓸한")
                        binding.mypageResettingKeywordSsupssupBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_ontherrock_bt -> {
                        favorkeyword.add("온더락")
                        binding.mypageResettingKeywordOntherrockBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_sangkum_bt -> {
                        favorkeyword.add("상큼한")
                        binding.mypageResettingKeywordSangkumBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_dansun_bt -> {
                        favorkeyword.add("단순한")
                        binding.mypageResettingKeywordDansunBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_milk_bt -> {
                        favorkeyword.add("우유")
                        binding.mypageResettingKeywordMilkBt.setTextColor(resources.getColor(R.color.black))
                    }
                    R.id.mypage_resetting_keyword_bockjap_bt -> {
                        favorkeyword.add("복잡한")
                        binding.mypageResettingKeywordBockjapBt.setTextColor(resources.getColor(R.color.black))
                    }
                }
            } else {
                when (buttonView.id) {
                    R.id.mypage_resetting_keyword_ladykiller_bt -> {
                        favorkeyword.remove("레이디킬러")
                    }
                    R.id.mypage_resetting_keyword_shooter_bt -> {
                        favorkeyword.remove("슈터")
                    }
                    R.id.mypage_resetting_keyword_clean_bt -> {
                        favorkeyword.remove("깔끔한")
                    }
                    R.id.mypage_resetting_keyword_tansan_bt -> {
                        favorkeyword.remove("탄산")
                    }
                    R.id.mypage_resetting_keyword_layered_bt -> {
                        favorkeyword.remove("레이어드")
                    }
                    R.id.mypage_resetting_keyword_martini_bt -> {
                        favorkeyword.remove("마티니")
                    }
                    R.id.mypage_resetting_keyword_pretty_bt -> {
                        favorkeyword.remove("예쁜")
                    }
                    R.id.mypage_resetting_keyword_highball_bt -> {
                        favorkeyword.remove("하이볼")
                    }
                    R.id.mypage_resetting_keyword_sweet_bt -> {
                        favorkeyword.remove("달달한")
                    }
                    R.id.mypage_resetting_keyword_dockhan_bt -> {
                        favorkeyword.remove("독한")
                    }
                    R.id.mypage_resetting_keyword_sangque_bt -> {
                        favorkeyword.remove("상쾌한")
                    }
                    R.id.mypage_resetting_keyword_fluitfavor_bt -> {
                        favorkeyword.remove("과일향")
                    }
                    R.id.mypage_resetting_keyword_ssupssup_bt -> {
                        favorkeyword.remove("씁쓸한")
                    }
                    R.id.mypage_resetting_keyword_ontherrock_bt -> {
                        favorkeyword.remove("온더락")
                    }
                    R.id.mypage_resetting_keyword_sangkum_bt -> {
                        favorkeyword.remove("상큼한")
                    }
                    R.id.mypage_resetting_keyword_dansun_bt -> {
                        favorkeyword.remove("단순한")
                    }
                    R.id.mypage_resetting_keyword_milk_bt -> {
                        favorkeyword.remove("우유")
                    }
                    R.id.mypage_resetting_keyword_bockjap_bt -> {
                        favorkeyword.remove("복잡한")
                    }
                }
            }
            Log.d("mypage_resetting_test", favorkeyword.toString())
            (activity as MainActivity).setMypageTempKeywords(favorkeyword)
        }
        binding.mypageResettingKeywordLadykillerBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordShooterBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordCleanBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordTansanBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordLayeredBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordMartiniBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordPrettyBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordHighballBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordSweetBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordDockhanBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordSangqueBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordFluitfavorBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordSsupssupBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordOntherrockBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordSangkumBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordDansunBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordMilkBt.setOnCheckedChangeListener(favorListner)
        binding.mypageResettingKeywordBockjapBt.setOnCheckedChangeListener(favorListner)

    }

    private fun initSelected(favorkeyword: ArrayList<String>){
        val favorTemp = arrayListOf(
            binding.mypageResettingKeywordLadykillerBt,
            binding.mypageResettingKeywordShooterBt,
            binding.mypageResettingKeywordCleanBt,
            binding.mypageResettingKeywordTansanBt,
            binding.mypageResettingKeywordLayeredBt,
            binding.mypageResettingKeywordMartiniBt,
            binding.mypageResettingKeywordPrettyBt,
            binding.mypageResettingKeywordHighballBt,
            binding.mypageResettingKeywordSweetBt,
            binding.mypageResettingKeywordDockhanBt,
            binding.mypageResettingKeywordSangqueBt,
            binding.mypageResettingKeywordFluitfavorBt,
            binding.mypageResettingKeywordSsupssupBt,
            binding.mypageResettingKeywordOntherrockBt,
            binding.mypageResettingKeywordSangkumBt,
            binding.mypageResettingKeywordDansunBt,
            binding.mypageResettingKeywordMilkBt,
            binding.mypageResettingKeywordBockjapBt)
        for (favor in favorTemp){
            favor.isChecked = favor.text in favorkeyword
        }
    }


}