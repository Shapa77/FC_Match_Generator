    package com.shapacreations.generatorfifa22 //Назва пакету

//Імпорт потрібних класів
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.shapacreations.generatorfifa22.databinding.FragmentAdvancedGenerateBinding
//-------------------------------------------------------------------------------------//


class AdvancedGenerateFragment : Fragment() {

    //Оголошення змінних
    private val binding by lazy { FragmentAdvancedGenerateBinding.inflate(layoutInflater) }

    private lateinit var context: Context

    private lateinit var selectedCountryForFilter1:String
    private lateinit var selectedDivisionForFilter1:String

    private lateinit var selectedCountryForFilter2:String
    private lateinit var selectedDivisionForFilter2:String

    private lateinit var advancedListFirstClubStars:List<String>
    private lateinit var advancedListSecondClubStars:List<String>

    private lateinit var advancedListMinimumStrengthStars1:List<String>
    private lateinit var advancedListMaximumStrengthStars1:List<String>
    private lateinit var advancedListMinimumStrengthStars2:List<String>
    private lateinit var advancedListMaximumStrengthStars2:List<String>


    private lateinit var selectedSexForFilter:String

    private val divisionListForSpinner1:ArrayList<String> = arrayListOf()
    private val divisionListForSpinner2:ArrayList<String> = arrayListOf()

    private val selectedClubsList1 = mutableListOf<ClubModel>()
    private val selectedClubsList2 = mutableListOf<ClubModel>()

    private var minStarSet1 = StrengthValue.HALF.value
    private var maxStarSet1 = StrengthValue.FIVE.value
    private var minStarSet2 = StrengthValue.HALF.value
    private var maxStarSet2 = StrengthValue.FIVE.value

    private var minimumStrengthForFilter1 = StrengthValue.HALF.value
    private var maximumStrengthForFilter1 = StrengthValue.FIVE.value
    private var minimumStrengthForFilter2 = StrengthValue.HALF.value
    private var maximumStrengthForFilter2 = StrengthValue.FIVE.value

    private val minimumStrengthStars1 = mutableListOf(false, false, false, false, false)
    private val maximumStrengthStars1 = mutableListOf(false, false, false, false, false)
    private val minimumStrengthStars2 = mutableListOf(false, false, false, false, false)
    private val maximumStrengthStars2 = mutableListOf(false, false, false, false, false)
    //-------------------------------------------------------------------------------------//


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View { return binding.root } //Стандартна функція створення фрагменту


    //Стандартна функція, коли фрагмент створено
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValues() //Ініціалізація змінних
        initSpinners() //Ініціалізація випадаючих списків

        binding.apply {

            advancedFilter.setOnClickListener { changeFragment(GenerateFragment(), gameId,false) } //Дія при натисканні кнопки розширеного фільтра - зміна фрагменту на стандартний фільтр

            //Перевірка поточного стану натиснення на зірки та встановлення відповідного значення для рейтингу
            advancedLayoutFirstClubMinimumStrengthStar1.setOnClickListener {
                if(!minimumStrengthStars1[0]){ setMinimumStrengthClick1(true,StrengthValue.ONE.value,1) }
                else{setMinimumStrengthClick1(false,StrengthValue.HALF.value,1) }
            }
            advancedLayoutFirstClubMinimumStrengthStar2.setOnClickListener {
                if(!minimumStrengthStars1[1]){ setMinimumStrengthClick1(true,StrengthValue.ONE_AND_HALF.value,2) }
                else{setMinimumStrengthClick1(false,StrengthValue.TWO.value,2) }
            }
            advancedLayoutFirstClubMinimumStrengthStar3.setOnClickListener {
                if(!minimumStrengthStars1[2]){ setMinimumStrengthClick1(true,StrengthValue.TWO_AND_HALF.value,3) }
                else{setMinimumStrengthClick1(false,StrengthValue.THREE.value,3) }
            }
            advancedLayoutFirstClubMinimumStrengthStar4.setOnClickListener {
                if(!minimumStrengthStars1[3]){ setMinimumStrengthClick1(true,StrengthValue.THREE_AND_HALF.value,4) }
                else{setMinimumStrengthClick1(false,StrengthValue.FOUR.value,4) }
            }
            advancedLayoutFirstClubMinimumStrengthStar5.setOnClickListener {
                if(!minimumStrengthStars1[4]){ setMinimumStrengthClick1(true,StrengthValue.FOUR_AND_HALF.value,5) }
                else{setMinimumStrengthClick1(false,StrengthValue.FIVE.value,5) }
            }

            advancedLayoutFirstClubMaximumStrengthStar1.setOnClickListener {
                if(!maximumStrengthStars1[0]){ setMaximumStrengthClick1(true,StrengthValue.ONE.value,1) }
                else{ setMaximumStrengthClick1(false,StrengthValue.HALF.value,1) }
            }
            advancedLayoutFirstClubMaximumStrengthStar2.setOnClickListener {
                if(!maximumStrengthStars1[1]){ setMaximumStrengthClick1(true,StrengthValue.ONE_AND_HALF.value,2) }
                else{ setMaximumStrengthClick1(false,StrengthValue.TWO.value,2) }
            }
            advancedLayoutFirstClubMaximumStrengthStar3.setOnClickListener {
                if(!maximumStrengthStars1[2]){ setMaximumStrengthClick1(true,StrengthValue.TWO_AND_HALF.value,3) }
                else{ setMaximumStrengthClick1(false,StrengthValue.THREE.value,3) }
            }
            advancedLayoutFirstClubMaximumStrengthStar4.setOnClickListener {
                if(!maximumStrengthStars1[3]){ setMaximumStrengthClick1(true,StrengthValue.THREE_AND_HALF.value,4) }
                else{ setMaximumStrengthClick1(false,StrengthValue.FOUR.value,4) }
            }
            advancedLayoutFirstClubMaximumStrengthStar5.setOnClickListener {
                if(!maximumStrengthStars1[4]){ setMaximumStrengthClick1(true,StrengthValue.FOUR_AND_HALF.value,5) }
                else{ setMaximumStrengthClick1(false,StrengthValue.FIVE.value,5) }
            }

            advancedLayoutSecondClubMinimumStrengthStar1.setOnClickListener {
                if(!minimumStrengthStars2[0]){ setMinimumStrengthClick2(true,StrengthValue.ONE.value,1) }
                else{setMinimumStrengthClick2(false,StrengthValue.HALF.value,1) }
            }
            advancedLayoutSecondClubMinimumStrengthStar2.setOnClickListener {
                if(!minimumStrengthStars2[1]){ setMinimumStrengthClick2(true,StrengthValue.ONE_AND_HALF.value,2) }
                else{setMinimumStrengthClick2(false,StrengthValue.TWO.value,2) }
            }
            advancedLayoutSecondClubMinimumStrengthStar3.setOnClickListener {
                if(!minimumStrengthStars2[2]){ setMinimumStrengthClick2(true,StrengthValue.TWO_AND_HALF.value,3) }
                else{setMinimumStrengthClick2(false,StrengthValue.THREE.value,3) }
            }
            advancedLayoutSecondClubMinimumStrengthStar4.setOnClickListener {
                if(!minimumStrengthStars2[3]){ setMinimumStrengthClick2(true,StrengthValue.THREE_AND_HALF.value,4) }
                else{setMinimumStrengthClick2(false,StrengthValue.FOUR.value,4) }
            }
            advancedLayoutSecondClubMinimumStrengthStar5.setOnClickListener {
                if(!minimumStrengthStars2[4]){ setMinimumStrengthClick2(true,StrengthValue.FOUR_AND_HALF.value,5) }
                else{setMinimumStrengthClick2(false,StrengthValue.FIVE.value,5) }
            }

            advancedLayoutSecondClubMaximumStrengthStar1.setOnClickListener {
                if(!maximumStrengthStars2[0]){ setMaximumStrengthClick2(true,StrengthValue.ONE.value,1) }
                else{ setMaximumStrengthClick2(false,StrengthValue.HALF.value,1) }
            }
            advancedLayoutSecondClubMaximumStrengthStar2.setOnClickListener {
                if(!maximumStrengthStars2[1]){ setMaximumStrengthClick2(true,StrengthValue.ONE_AND_HALF.value,2) }
                else{ setMaximumStrengthClick2(false,StrengthValue.TWO.value,2) }
            }
            advancedLayoutSecondClubMaximumStrengthStar3.setOnClickListener {
                if(!maximumStrengthStars2[2]){ setMaximumStrengthClick2(true,StrengthValue.TWO_AND_HALF.value,3) }
                else{ setMaximumStrengthClick2(false,StrengthValue.THREE.value,3) }
            }
            advancedLayoutSecondClubMaximumStrengthStar4.setOnClickListener {
                if(!maximumStrengthStars2[3]){ setMaximumStrengthClick2(true,StrengthValue.THREE_AND_HALF.value,4) }
                else{ setMaximumStrengthClick2(false,StrengthValue.FOUR.value,4) }
            }
            advancedLayoutSecondClubMaximumStrengthStar5.setOnClickListener {
                if(!maximumStrengthStars2[4]){ setMaximumStrengthClick2(true,StrengthValue.FOUR_AND_HALF.value,5) }
                else{ setMaximumStrengthClick2(false,StrengthValue.FIVE.value,5) }
            }
            //------------------------------------------------------------------------------------------//

            //Дія при натисканні на radioButton для зміни статі
            advancedSexManChoice.setOnClickListener {changeSexButton(man = true, all = false, woman = false)}
            advancedSexBothChoice.setOnClickListener {changeSexButton(man = false, all = true, woman = false)}
            advancedSexWomanChoice.setOnClickListener {changeSexButton(man = false, all = false, woman = true)}
            //------------------------------------------------------------------------------------------//

            //Дія при натисканні кнопки "Згенерувати"
            advancedGenerateButton.setOnClickListener {

                binding.advancedSexImage.visibility = View.VISIBLE //Відображення іконки статі матчу

                initList(context,selectedClubsList1,selectedCountryForFilter1,selectedDivisionForFilter1,minimumStrengthForFilter1, maximumStrengthForFilter1,selectedSexForFilter) //Ініціалізація списку команди 1 згідно фільтру
                initList(context,selectedClubsList2,selectedCountryForFilter2,selectedDivisionForFilter2,minimumStrengthForFilter2, maximumStrengthForFilter2,selectedSexForFilter) //Ініціалізація списку команди 2 згідно фільтру

                //Перевірка на розмір списку
                if (selectedClubsList1.isNotEmpty() && selectedClubsList2.isNotEmpty()) {

                    setClubs(checkClubs(rand(selectedClubsList1), rand(selectedClubsList2))) //Вивід команд на екран

                    selectedClubsList1.clear()//Очистка списку команд 1
                    selectedClubsList2.clear()//Очистка списку команд 2
                }
                else {
                    Toast.makeText(context,R.string.Not_found, Toast.LENGTH_SHORT).show() //Вивід помилки "Не знайдено" (пустий список)
                }


            }
        }

    }

    //Оновлення змінних при зміні фільтру статі
    private fun changeSexButton(man:Boolean, all:Boolean, woman:Boolean){

        binding.apply {
            advancedSexManChoice.isChecked = man
            advancedSexBothChoice.isChecked = all
            advancedSexWomanChoice.isChecked = woman
        }
        //------------------------------------------------------------------------------------------//

        selectedSexForFilter = when {
            man -> getString(R.string.Man)
            woman -> getString(R.string.Woman)
            else -> getString(R.string.All_sex)
        }

        countryListForSpinnerSex.clear()//Очистка списку країн для фільтру
        initSpinners()//Ініціалізація випадаючих списків
        setDefaultClubs()//Вивід даних стандартних команд у відповідні поля

    }

    //Ініціалізація змінних
    private fun initValues(){


        selectedSexForFilter = when {
            binding.advancedSexManChoice.isChecked -> getString(R.string.Man)
            binding.advancedSexWomanChoice.isChecked -> getString(R.string.Woman)
            else -> getString(R.string.All_sex)

        }

        //Ініціалізація списків з ідентифікаторами кнопок-зірок
        advancedListFirstClubStars = listOf(getString(R.string.advancedFirstClubStar1), getString(R.string.advancedFirstClubStar2), getString(R.string.advancedFirstClubStar3), getString(R.string.advancedFirstClubStar4), getString(R.string.advancedFirstClubStar5))
        advancedListSecondClubStars = listOf(getString(R.string.advancedSecondClubStar1), getString(R.string.advancedSecondClubStar2), getString(R.string.advancedSecondClubStar3), getString(R.string.advancedSecondClubStar4), getString(R.string.advancedSecondClubStar5))
        advancedListMinimumStrengthStars1 = listOf(getString(R.string.advancedLayoutFirstClubMinimumStrengthStar1), getString(R.string.advancedLayoutFirstClubMinimumStrengthStar2), getString(R.string.advancedLayoutFirstClubMinimumStrengthStar3), getString(R.string.advancedLayoutFirstClubMinimumStrengthStar4), getString(R.string.advancedLayoutFirstClubMinimumStrengthStar5))
        advancedListMaximumStrengthStars1 = listOf(getString(R.string.advancedLayoutFirstClubMaximumStrengthStar1), getString(R.string.advancedLayoutFirstClubMaximumStrengthStar2), getString(R.string.advancedLayoutFirstClubMaximumStrengthStar3), getString(R.string.advancedLayoutFirstClubMaximumStrengthStar4), getString(R.string.advancedLayoutFirstClubMaximumStrengthStar5))
        advancedListMinimumStrengthStars2 = listOf(getString(R.string.advancedLayoutSecondClubMinimumStrengthStar1), getString(R.string.advancedLayoutSecondClubMinimumStrengthStar2), getString(R.string.advancedLayoutSecondClubMinimumStrengthStar3), getString(R.string.advancedLayoutSecondClubMinimumStrengthStar4), getString(R.string.advancedLayoutSecondClubMinimumStrengthStar5))
        advancedListMaximumStrengthStars2 = listOf(getString(R.string.advancedLayoutSecondClubMaximumStrengthStar1), getString(R.string.advancedLayoutSecondClubMaximumStrengthStar2), getString(R.string.advancedLayoutSecondClubMaximumStrengthStar3), getString(R.string.advancedLayoutSecondClubMaximumStrengthStar4), getString(R.string.advancedLayoutSecondClubMaximumStrengthStar5))
        //------------------------------------------------------------------------------------------//

    }

    //Ініціалізація змінних-списків
    private fun initValuesData(){

        //Ініціалізація списку країн для випадаючих списків
        countryListForSpinner = when (gameId) {
            GameId.FC24.ordinal -> countryListForSpinnerFifa24
            GameId.FIFA23.ordinal -> countryListForSpinnerFifa23
            GameId.FIFA22.ordinal -> countryListForSpinnerFifa22
            GameId.FC25.ordinal -> countryListForSpinnerFifa25
            else -> countryListForSpinnerFifa25
        }

        //Ініціалізація списку команд (витягнення з файлу json)
        clubList = when (gameId) {
            GameId.FC24.ordinal -> loadClubsFromJson(context,getString(R.string.clubs24_json))
            GameId.FIFA23.ordinal -> loadClubsFromJson(context,getString(R.string.clubs23_json))
            GameId.FIFA22.ordinal -> loadClubsFromJson(context,getString(R.string.clubs22_json))
            GameId.FC25.ordinal -> loadClubsFromJson(context,getString(R.string.clubs25_json))
            else -> loadClubsFromJson(context,getString(R.string.clubs25_json))
        }


    }

    //Ініціалізація випадаючих списків
    private fun initSpinners(){

        countryListForSpinnerSex.clear()//Очистка списку країн для фільтру

        //Ініціалізація списку команд для фільтру в залежності від обраної статі
        when(selectedSexForFilter){
            getString(R.string.All_sex), getString(R.string.Man)->countryListForSpinnerSex.addAll(countryListForSpinner)
            getString(R.string.Woman)-> countryListForSpinnerSex.addAll(countryListForSpinner.filter { it.female })
        }

        if(countryListForSpinnerSex.size == 2) countryListForSpinnerSex.removeFirstOrNull() //Видалення пункту "Всі країни", якщо у списку тільки одна країна


        initSpinnersClub1() //Ініціалізація випадаючих списків для команди 1
        initSpinnersClub2() //Ініціалізація випадаючих списків для команди 2
        initSpinnerGame() //Ініціалізація випадаючого списку для зміни версії гри
    }

    //Ініціалізація випадаючих списків для команди 1
    private fun initSpinnersClub1(){

        val spinnerCountryAdapter = CountrySpinnerAdapter(context,countryListForSpinnerSex) // Ініціалізація адаптеру випадаючого списку країн
        val spinnerDivisionAdapter = DivisionSpinnerAdapter(context, divisionListForSpinner1) // Ініціалізація адаптеру випадаючого списку ліг

        selectedCountryForFilter1 = getStandardStringByValue(context,getString(R.string.All_countries)) //Ініціалізація змінної обраної країни для фільтру (береться значення без врахування локалізації)
        selectedDivisionForFilter1 = getStandardStringByValue(context,getString(R.string.All_divisions))//Ініціалізація змінної обраної ліги для фільтру (береться значення без врахування локалізації)

        divisionListForSpinner1.apply {
            clear() //Очищення списку ліг для випадаючого списку
            add(getString(R.string.All_divisions)) //Додавання пункту "Всі ліги" до списку ліг для випадаючого списку
        }

        binding.apply {

            advancedLayoutFirstClubSpinnerCountry.adapter = spinnerCountryAdapter //Присвоєння адаптеру для випадаючого списку країн
            advancedLayoutFirstClubSpinnerDivision.adapter = spinnerDivisionAdapter //Присвоєння адаптеру для випадаючого списку ліг

            //Дія при виборі елементу випадаючого списку країн
            advancedLayoutFirstClubSpinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    selectedCountryForFilter1 = getStandardStringByValue(context,countryListForSpinnerSex[position].text) //Ініціалізація змінної обраної країни для фільтру (береться значення без врахування локалізації)
                    selectedDivisionForFilter1 = getStandardStringByValue(context,getString(R.string.All_divisions)) //Ініціалізація змінної обраної ліги для фільтру (береться значення без врахування локалізації)

                    divisionListForSpinner1.apply {
                        clear() //Очищення списку ліг для випадаючого списку
                        add(getString(R.string.All_divisions)) //Додавання елементу "Всі ліги" до випадаючого списку ліг
                    }


                    //Заповнення випадаючого списку ліг в залежності від обраної статі
                    clubList.filter { club ->
                        club.country == selectedCountryForFilter1 && (selectedSexForFilter == getString(R.string.All_sex) || club.sex == selectedSexForFilter)}
                        .map { club -> club.division }
                        .distinct()
                        .forEach { division -> divisionListForSpinner1.add(division) }


                    if (divisionListForSpinner1.size == 2) { divisionListForSpinner1.removeAt(0) }  //Видалення зі списку пункту "Всі ліги", якщо ліга лише одна (два елемента в списку)

                    spinnerDivisionAdapter.notifyDataSetChanged() //Оновлення випадаючого списку
                    binding.advancedLayoutFirstClubSpinnerDivision.setSelection(0) //Вибір у випадаючий список елемент з індексом 0 (перший в списку)
                    setStrengthMinMaxStar1() //Встановлення мінімального та максимального значення для списку команд в залежності від фільтрів
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {} //Дія, якщо не обрано нічого

            }

            //Дія при виборі елементу випадаючого списку ліг
            advancedLayoutFirstClubSpinnerDivision.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    //Ініціалізація змінної обраної ліги для фільтру
                    selectedDivisionForFilter1 = if(divisionListForSpinner1[position] == getString(R.string.All_divisions)){ //Якщо значення "Всі ліги"
                        getStandardStringByValue(context,divisionListForSpinner1[position])//Брати значення без врахування локалізації
                    } else divisionListForSpinner1[position] //Якщо ні - брати значення з урахуванням локалізації
                    setStrengthMinMaxStar1() //Встановлення мінімального та максимального значення для списку команд в залежності від фільтрів
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {} //Дія, якщо не обрано нічого

            }
        }
    }

    //Ініціалізація випадаючих списків для команди 2
    private fun initSpinnersClub2(){

        val spinnerCountryAdapter = CountrySpinnerAdapter(context,countryListForSpinnerSex) // Ініціалізація адаптеру випадаючого списку країн
        val spinnerDivisionAdapter = DivisionSpinnerAdapter(context, divisionListForSpinner2) // Ініціалізація адаптеру випадаючого списку ліг

        selectedCountryForFilter2 = getStandardStringByValue(context,getString(R.string.All_countries)) //Ініціалізація змінної обраної країни для фільтру (береться значення без врахування локалізації)
        selectedDivisionForFilter2 = getStandardStringByValue(context,getString(R.string.All_divisions)) //Ініціалізація змінної обраної ліги для фільтру (береться значення без врахування локалізації)

        divisionListForSpinner2.apply {
            clear() //Очищення списку ліг для випадаючого списку
            add(getString(R.string.All_divisions)) //Додавання елементу "Всі ліги" до випадаючого списку ліг
        }

        binding.apply {

            advancedLayoutSecondClubSpinnerCountry.adapter = spinnerCountryAdapter  //Присвоєння адаптеру для випадаючого списку країн
            advancedLayoutSecondClubSpinnerDivision.adapter = spinnerDivisionAdapter //Присвоєння адаптеру для випадаючого списку ліг

            //Дія при виборі елементу випадаючого списку країн
            advancedLayoutSecondClubSpinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    selectedCountryForFilter2 = getStandardStringByValue(context,countryListForSpinnerSex[position].text) //Ініціалізація змінної обраної країни для фільтру (береться значення без врахування локалізації)
                    selectedDivisionForFilter2 = getStandardStringByValue(context,getString(R.string.All_divisions)) //Ініціалізація змінної обраної ліги для фільтру (береться значення без врахування локалізації)

                    divisionListForSpinner2.apply {
                        clear() //Очищення списку ліг для випадаючого списку
                        add(getString(R.string.All_divisions)) //Додавання елементу "Всі ліги" до випадаючого списку ліг
                    }

                    //Заповнення випадаючого списку ліг в залежності від обраної статі
                    clubList.filter { club ->
                        club.country == selectedCountryForFilter2 && (selectedSexForFilter == getString(R.string.All_sex) || club.sex == selectedSexForFilter)}
                        .map { club -> club.division }
                        .distinct()
                        .forEach { division -> divisionListForSpinner2.add(division) }


                    if (divisionListForSpinner2.size == 2) { divisionListForSpinner2.removeAt(0) }  //Видалення зі списку пункту "Всі ліги", якщо ліга лише одна (два елемента в списку)
                    spinnerDivisionAdapter.notifyDataSetChanged() //Оновлення випадаючого списку

                    binding.advancedLayoutSecondClubSpinnerDivision.setSelection(0) //Вибір у випадаючий список елемент з індексом 0 (перший в списку)
                    setStrengthMinMaxStar2() //Встановлення мінімального та максимального значення для списку команд в залежності від фільтрів
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
            advancedLayoutSecondClubSpinnerDivision.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedDivisionForFilter2 = if(divisionListForSpinner2[position] == getString(R.string.All_divisions)){
                        getStandardStringByValue(context,divisionListForSpinner2[position])
                    } else divisionListForSpinner2[position]
                    setStrengthMinMaxStar2()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
        }
    }

    //Ініціалізація випадаючого списку для зміни версії гри
    private fun initSpinnerGame(){

        val spinnerGameAdapter = GameSpinnerAdapter(context, gameIconsForSpinner)

        binding.apply {
            advancedSpinnerGame.adapter = spinnerGameAdapter
            advancedSpinnerGame.setSelection(gameId)
            advancedSpinnerGame.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if(gameId != position){
                        gameId = position
                        initValues()
                        initValuesData()
                        initSpinners()
                        setDefaultClubs()
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
        }

    }

    //Перевірка згенерованих команд
    private fun checkClubs(randFirstClub: Int, randSecondClub: Int) :RandClubs {
        var firstClubRand = randFirstClub
        var secondClubRand = randSecondClub

        if(selectedClubsList1[firstClubRand].sex != selectedClubsList2[secondClubRand].sex){
            if((selectedClubsList1.any{it.sex == getString(R.string.Man)} && selectedClubsList2[secondClubRand].sex == getString(R.string.Man)) ||
                (selectedClubsList1.any{it.sex == getString(R.string.Woman)} && selectedClubsList2[secondClubRand].sex == getString(R.string.Woman)) ||
                (selectedClubsList2.any{it.sex == getString(R.string.Man)} && selectedClubsList1[firstClubRand].sex == getString(R.string.Man)) ||
                (selectedClubsList2.any{it.sex == getString(R.string.Woman)} && selectedClubsList1[firstClubRand].sex == getString(R.string.Woman))){

                while  ((selectedClubsList1.size>1 && selectedClubsList2.size>1) &&
                    ((selectedClubsList1[firstClubRand].sex != selectedClubsList2[secondClubRand].sex))){
                    firstClubRand = rand(selectedClubsList1)
                    secondClubRand = rand(selectedClubsList2)
                }
            }
            else{
                showToast(context,R.string.Not_found)
                return RandClubs(-1,-1)}

        }

        return RandClubs(firstClubRand,secondClubRand)
    }

    //Вивід даних згенерованих команд у відповідні поля
    private fun setClubs(randClubs: RandClubs){

        if(randClubs.firstClub != -1){

            val strength1 = getStrengthValue(selectedClubsList1[randClubs.firstClub].strength)
            val strength2 = getStrengthValue(selectedClubsList2[randClubs.secondClub].strength)

            binding.apply {
                advancedLogoFirstClub.setImageResource(selectedClubsList1[randClubs.firstClub].logoId)
                advancedNameFirstClub.text = selectedClubsList1[randClubs.firstClub].name
                advancedDivisionFirstClub.text = selectedClubsList1[randClubs.firstClub].division
                advancedCountryFirstClub.text = getLocalizedStringByValue(context,selectedClubsList1[randClubs.firstClub].country)

                advancedLogoSecondClub.setImageResource(selectedClubsList2[randClubs.secondClub].logoId)
                advancedNameSecondClub.text = selectedClubsList2[randClubs.secondClub].name
                advancedDivisionSecondClub.text = selectedClubsList2[randClubs.secondClub].division
                advancedCountrySecondClub.text = getLocalizedStringByValue(context,selectedClubsList2[randClubs.secondClub].country)
            }

            when(selectedClubsList1[randClubs.firstClub].sex){
                getString(R.string.Man)-> binding.advancedSexImage.setImageResource(R.drawable.male_icon)
                getString(R.string.Woman)->binding.advancedSexImage.setImageResource(R.drawable.female_icon)
            }

            strengthStarSet(binding,strength1,advancedListFirstClubStars)
            strengthStarSet(binding,strength2,advancedListSecondClubStars)

        }
    }

    //Вивід граничних рейтингів списку команд 1 згідно фільтрів
    private fun setStrengthMinMaxStar1(){

        minimumStrengthForFilter1 = StrengthValue.HALF.value
        maximumStrengthForFilter1 = StrengthValue.FIVE.value

        initList(context,selectedClubsList1,selectedCountryForFilter1,selectedDivisionForFilter1,minimumStrengthForFilter1, maximumStrengthForFilter1,selectedSexForFilter)

        minStarSet1 = selectedClubsList1.minOf { it.strength}
        maxStarSet1 = selectedClubsList1.maxOf { it.strength}

        strengthStarSet(binding,minStarSet1,advancedListMinimumStrengthStars1)
        strengthStarSet(binding,maxStarSet1,advancedListMaximumStrengthStars1)

        minimumStrengthForFilter1 = minStarSet1
        maximumStrengthForFilter1 = maxStarSet1

        selectedClubsList1.clear()

    }

    //Вивід граничних рейтингів списку команд 2 згідно фільтрів
    private fun setStrengthMinMaxStar2(){

        minimumStrengthForFilter2 = StrengthValue.HALF.value
        maximumStrengthForFilter2 = StrengthValue.FIVE.value

        initList(context,selectedClubsList2,selectedCountryForFilter2,selectedDivisionForFilter2,minimumStrengthForFilter2, maximumStrengthForFilter2,selectedSexForFilter)

        minStarSet2 = selectedClubsList2.minOf { it.strength}
        maxStarSet2 = selectedClubsList2.maxOf { it.strength}

        strengthStarSet(binding,minStarSet2,advancedListMinimumStrengthStars2)
        strengthStarSet(binding,maxStarSet2,advancedListMaximumStrengthStars2)

        minimumStrengthForFilter2 = minStarSet2
        maximumStrengthForFilter2 = maxStarSet2

        selectedClubsList2.clear()

    }

    //Встановлення мінімального рейтингу команди 1 для фільтру
    private fun setMinimumStrengthClick1(starBool:Boolean,strength: Double,starCount:Int){


        strengthStarSet(binding,strength,advancedListMinimumStrengthStars1)
        minimumStrengthForFilter1 = strength

        if (starCount in 1..5) { minimumStrengthStars1[starCount - 1] = starBool }

        when {
            minimumStrengthForFilter1 < minStarSet1 -> {
                strengthStarSet(binding, minStarSet1, advancedListMinimumStrengthStars1)
                minimumStrengthForFilter1 = minStarSet1
            }
            minimumStrengthForFilter1 > maxStarSet1 -> {
                strengthStarSet(binding, maxStarSet1, advancedListMinimumStrengthStars1)
                minimumStrengthForFilter1 = maxStarSet1
            }
        }

        if(minimumStrengthForFilter1 > maximumStrengthForFilter1){
            maximumStrengthForFilter1 = minimumStrengthForFilter1
            strengthStarSet(binding,maximumStrengthForFilter1,advancedListMaximumStrengthStars1)
        }



    }

    //Встановлення мінімального рейтингу команди 2 для фільтру
    private fun setMinimumStrengthClick2(starBool:Boolean,strength: Double,starCount:Int){

        strengthStarSet(binding, strength, advancedListMinimumStrengthStars2)
        minimumStrengthForFilter2 = strength

        if (starCount in 1..5) { minimumStrengthStars2[starCount - 1] = starBool }
        when {
            minimumStrengthForFilter2 < minStarSet2 -> {
                strengthStarSet(binding, minStarSet2, advancedListMinimumStrengthStars2)
                minimumStrengthForFilter2 = minStarSet2
            }
            minimumStrengthForFilter2 > maxStarSet2 -> {
                strengthStarSet(binding, maxStarSet2, advancedListMinimumStrengthStars2)
                minimumStrengthForFilter2 = maxStarSet2
            }
        }


        if(minimumStrengthForFilter2 > maximumStrengthForFilter2){
            maximumStrengthForFilter2 = minimumStrengthForFilter2
            strengthStarSet(binding, maximumStrengthForFilter2, advancedListMaximumStrengthStars2)
        }

    }

    //Встановлення максимального рейтингу команди 1 для фільтру
    private fun setMaximumStrengthClick1(starBool:Boolean,strength: Double,starCount:Int){

        strengthStarSet(binding, strength, advancedListMaximumStrengthStars1)
        maximumStrengthForFilter1 = strength

        if (starCount in 1..5) { maximumStrengthStars1[starCount - 1] = starBool }

        when {
            maximumStrengthForFilter1 < minStarSet1 -> {
                strengthStarSet(binding, minStarSet1, advancedListMaximumStrengthStars1)
                maximumStrengthForFilter1 = minStarSet1
            }
            maximumStrengthForFilter1 > maxStarSet1 -> {
                strengthStarSet(binding, maxStarSet1, advancedListMaximumStrengthStars1)
                maximumStrengthForFilter1 = maxStarSet1
            }
        }


        if(minimumStrengthForFilter1 > maximumStrengthForFilter1){
            minimumStrengthForFilter1 = maximumStrengthForFilter1
            strengthStarSet(binding, maximumStrengthForFilter1, advancedListMaximumStrengthStars1)
            strengthStarSet(binding, minimumStrengthForFilter1, advancedListMinimumStrengthStars1)
        }




    }

    //Встановлення максимального рейтингу команди 2 для фільтру
    private fun setMaximumStrengthClick2(starBool:Boolean,strength: Double,starCount:Int){

        strengthStarSet(binding, strength, advancedListMaximumStrengthStars2)
        maximumStrengthForFilter2 = strength

        if (starCount in 1..5) { maximumStrengthStars2[starCount - 1] = starBool }


        when {
            maximumStrengthForFilter2 < minStarSet2 -> {
                strengthStarSet(binding, minStarSet2, advancedListMaximumStrengthStars2)
                maximumStrengthForFilter2 = minStarSet2
            }
            maximumStrengthForFilter2 > maxStarSet2 -> {
                strengthStarSet(binding, maxStarSet2, advancedListMaximumStrengthStars2)
                maximumStrengthForFilter2 = maxStarSet2
            }
        }


        if(minimumStrengthForFilter2 > maximumStrengthForFilter2){
            minimumStrengthForFilter2 = maximumStrengthForFilter2
            strengthStarSet(binding, maximumStrengthForFilter2, advancedListMaximumStrengthStars2)
            strengthStarSet(binding, minimumStrengthForFilter2, advancedListMinimumStrengthStars2)
        }
    }

    //Вивід даних стандартних команд у відповідні поля
    private fun setDefaultClubs(){

        binding.apply {

            resetClubInfo(advancedLogoFirstClub, advancedNameFirstClub, advancedDivisionFirstClub, advancedCountryFirstClub)
            resetClubInfo(advancedLogoSecondClub, advancedNameSecondClub, advancedDivisionSecondClub, advancedCountrySecondClub)

            advancedSexImage.visibility = View.INVISIBLE
        }

        strengthStarSet(binding, StrengthValue.ZERO.value, advancedListFirstClubStars)
        strengthStarSet(binding, StrengthValue.ZERO.value, advancedListSecondClubStars)
    }

    //Отримання контексту Activity (ініціалізація змінної "context")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = requireContext()
    }

}