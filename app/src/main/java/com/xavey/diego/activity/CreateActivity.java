package com.xavey.diego.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.xavey.diego.R;
import com.xavey.diego.api.model.Meller;
import com.xavey.diego.helper.AppValues;
import com.xavey.diego.helper.DBHelper;
import com.xavey.diego.helper.UtilityHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateActivity extends AppCompatActivity {

    public static final String P_NUMBER = "";
    private String preState = "Current State/Region";
    private String preCity = "Current City/Township";
    private String preEduc = "Education Level";
    private String preMarital = "Marital Status";
    private String preEmployment = "Employment";
    private String preIncome = "Income Range";
    private String preCareer ="Career Level";
    private String preJobFunc = "Job Function";
    private String preIndustry = "Industry";
    private String preTelco = "Telco Used";
    private String preReligion = "Religion";
    private String preRace = "Race";

    private DatePickerDialog dpDOB;
    private SimpleDateFormat dateFormatter;
    private TextView txtDOB;

    String[] citySet = {preState,
            "Ayeyarwady",
            "Bago (East)",
            "Bago (West)",
            "Chin",
            "Kachin",
            "Kayah",
            "Kayin",
            "Magway",
            "Mandalay",
            "Mon",
            "Rakhine",
            "Sagaing",
            "Shan (East)",
            "Shan (North)",
            "Shan (South)",
            "Tanintharyi",
            "Yangon"};

    String[] blackState = {preCity};

    String[] yangonDivision = {preCity,
            "Botahtaung",
            "Dagon Myothit (East)",
            "Dagon Myothit (North)",
            "Dagon Myothit (Seikkan)",
            "Dagon Myothit (South)",
            "Dawbon",
            "Mingalartaungnyunt",
            "North Okkalapa",
            "Pazundaung",
            "South Okkalapa",
            "Tamwe",
            "Thaketa",
            "Thingangyun",
            "Yankin",
            "Hlaingtharya",
            "Hlegu",
            "Hmawbi",
            "Htantabin (Yangon)",
            "Insein",
            "Mingaladon",
            "Shwepyithar",
            "Taikkyi",
            "Cocokyun",
            "Dala",
            "Kawhmu",
            "Kayan",
            "Kungyangon",
            "Kyauktan",
            "Seikgyikanaungto",
            "Thanlyin",
            "Thongwa",
            "Twantay",
            "Ahlone",
            "Bahan",
            "Dagon",
            "Hlaing",
            "Kamaryut",
            "Kyauktada",
            "Kyeemyindaing",
            "Lanmadaw",
            "Latha",
            "Mayangone",
            "Pabedan",
            "Sanchaung",
            "Seikkan"};

    String[] mandalayDivision ={preCity,
            "Amarapura",
            "Aungmyaythanzan",
            "Chanayethazan",
            "Chanmyathazi",
            "Kyaukpadaung",
            "Kyaukse",
            "Madaya",
            "Mahaaungmyay",
            "Mahlaing",
            "Meiktila",
            "Mongoke",
            "Myingyan",
            "Myittha",
            "Natogyi",
            "Nay Pyi Taw - Lewe",
            "Nay Pyi Taw - Pyimana",
            "Nay Pyi Taw - Tatkon",
            "Ngazun",
            "Nyaung-U",
            "Patheingyi",
            "Pyawbwe",
            "Pyigyitagon",
            "Pyinoolwin",
            "Singu",
            "Sintgaing",
            "Tada-U",
            "Taungtha",
            "Thabeikkyin",
            "Thazi",
            "Wundwin",
            "Yamethin"};

    String[] sagaingDivision={preCity,
            "Ayadaw",
            "Banmauk",
            "Budalin",
            "Chanung-U",
            "Hkamti",
            "Homalin",
            "Indaw",
            "Kale",
            "Kalewa",
            "Kanbalu",
            "Kani",
            "Katha",
            "Kawlin",
            "Khin-U",
            "Kyunhla",
            "Lahe",
            "LayShi",
            "Mawlaik",
            "Mingin",
            "Monywa",
            "Myaung",
            "Myinmu",
            "Nanyun",
            "Pale",
            "Paungbyin",
            "Pinlebu",
            "Sagaing",
            "Salingyi",
            "Shwebo",
            "Tabayin",
            "Tamu",
            "Taze",
            "Tigyaing",
            "Wetlet",
            "Wuntho",
            "Ye-U",
            "Yinmabin"};

    String[] magwayDivision={preCity,
            "Magway",
            "Gangaw",
            "Tilin",
            "Myaing",
            "Yesagyo",
            "Pauk",
            "Pakokku",
            "Saw",
            "Seikphyu",
            "Chauk",
            "Salin",
            "Sidoktaya",
            "Natmauk",
            "Yenangyaung",
            "Pwintphyu",
            "Myothit",
            "Minbu",
            "Ngape",
            "Taungdwingyi",
            "Minhla",
            "Sinbaungwe",
            "Thayet",
            "Mindon",
            "Aunglan",
            "Kamma"};

    String[] ayeyarwadyDivision={preCity,"Hinthada",
            "Ingapu",
            "Kyangin",
            "Lemyethna",
            "Myanaung",
            "Zalun",
            "Labutta",
            "Mawlamyinegyun",
            "Danubyu",
            "Maubin",
            "Nyaungdon",
            "Pantanaw",
            "Einme",
            "Myaungmya",
            "Wakema",
            "Kangyidaunt",
            "Kyaunggon",
            "Kyonpyaw",
            "Ngapudaw",
            "Pathein",
            "Thabaung",
            "Yegyi",
            "Bogale",
            "Dedaye",
            "Kyaiklat",
            "Pyapon"};

    String[] bagoEastDivision={preCity,
            "Taungoo",
            "Yedashe",
            "Oktwin",
            "Htantabin",
            "Phyu",
            "Kyaukkyi",
            "Kyauktaga",
            "Nyaunglebin",
            "Shwegyin",
            "Daik-U",
            "Bago",
            "Waw",
            "Thanatpin",
            "Kawa"};

    String[] bagoWestDivision={preCity,
            "Pyay",
            "Paukkhaung",
            "Padaung",
            "Paungde",
            "Thegon",
            "Shwedaung",
            "Nattalin",
            "Zigon",
            "Gyobingauk",
            "Okpho",
            "Monyo",
            "Minhla",
            "Letpadan",
            "Thayarwady"};

    String[] tanintharyiDivision={preCity,
            "Myeik",
            "Yebyu",
            "Launglon",
            "Dawei",
            "Thayetchaung",
            "Palaw",
            "Kyunsu",
            "Bokpyin",
            "Kawthoung"};

    String[] kachinState ={preCity,
            "Puta-O",
            "Nawngmun",
            "Machanbaw",
            "Khaunglanpu",
            "Tanai",
            "Sumprabum",
            "Tsawlaw",
            "Injangyang",
            "Chipwi",
            "Hpakan",
            "Myityina",
            "Mogaung",
            "Waingmaw",
            "Monhnyin",
            "Momauk",
            "Shwegu",
            "Bhamo",
            "Mansi"};

    String[] kayarState ={preCity,
            "Loikaw",
            "Shadaw",
            "Demoso",
            "Hpruso",
            "Bawlakhe",
            "Hpasawng",
            "Mese"};

    String[] kayinState ={preCity,
            "Hpapun",
            "Thandaunggyi",
            "Hlaingbwe",
            "Hpa-An",
            "Myawaddy",
            "Kawkareik",
            "Kyainsekgyi"};

    String[] chinState = {preCity,
            "Falam",
            "Hakha",
            "Htantlang",
            "Kanpetlet",
            "Madupi",
            "Mindat",
            "Paletwa",
            "Tidim",
            "Tonzang"};

    String[] monState ={preCity,
            "Bilin",
            "Chaungzon",
            "Kyaikmaraw",
            "Kyaikto",
            "Mawlamyine",
            "Mudon",
            "Paung",
            "Thanbyuzayat",
            "Thaton",
            "Ye"};

    String[] rakhineState ={preCity,
            "Ann",
            "Buthidaung",
            "Gwa",
            "Kyaukphyu",
            "Kyauktaw",
            "Mauk-U",
            "Maungdaw",
            "Minbya",
            "Munaung",
            "Myebon",
            "Pauktaw",
            "Ponagyun",
            "Ramree",
            "Rathedaung",
            "Sittwe",
            "Thandwe",
            "Toungup"};

    String[] shanEasteState={preCity,
            "Kengton",
            "Monghpyak",
            "Monghsat",
            "Mongkhet",
            "Mongla",
            "Mongping",
            "Mongton",
            "Mongyaung",
            "Mongyaung",
            "Tachileik"};

    String[] shanNorthState ={preCity,
            "Hopang",
            "Hsein",
            "Hsipaw",
            "Konkyaun",
            "Kunlong",
            "Kutkai",
            "Kyaukme",
            "Lashio",
            "Laukkaing",
            "Mabein",
            "Manton",
            "Matman",
            "Mongmao",
            "Mongmit",
            "Mongyai",
            "Muse",
            "Namhsan",
            "Namkan",
            "Namphan",
            "Namtu",
            "Nawnghkio",
            "Pangsang",
            "Pangwaun",
            "Tangyan"};

    String[] shanSouthState ={preCity,
            "Hopong",
            "Hsiheseng",
            "Kalaw",
            "Kengtung",
            "Kunhing",
            "Kyethi",
            "Laihka",
            "Langkho",
            "Lawksawk",
            "Loilen",
            "Mawkmai",
            "Monghsu",
            "Mongkaung",
            "Mongnai",
            "Mongpan",
            "Nansang",
            "Nyaungshwe",
            "Pekon",
            "Pindaya",
            "Pinlaung",
            "Taunggyi",
            "Ywangan"};

    String[] incomeSet ={preIncome,
            "Under 100,000",
            "100,000 - 250,000",
            "250,000 - 350,000",
            "350,000 - 500,000",
            "500,000 - 1,000,000",
            "Above 1,000,000"};

    String[] educationSet ={preEduc,
            "Illiterate",
            "Primary",
            "Middle",
            "High",
            "Diploma",
            "Graduated",
            "Post grad.",
            "Ph.D"};

    String[] maritalSet ={preMarital,
            "Single",
            "Married",
            "Separated",
            "Divorced",
            "Widow"};

    String[] empSet ={preEmployment,
            "Dependent",
            "Own Business",
            "Part-time",
            "Freelance/contract",
            "Full-time"};

    String[] careerSet ={preCareer,
            "NA",
            "Entry Level",
            "Experienced (Non-Management)",
            "Management",
            "Senior Management",
            "Director and Above"};

    String[] jobFuncSet={preJobFunc,"NA","Accounting/Finance","Administrative","Consulting","Customer Service/Support",
            "Design/Architecture","Driver/Security/Cleaning","Education/Teaching/Childcare",
            "Engineering/Technical","Fitness/Wellbeing","Food and Beverage",
            "Hair/Nails and Beauty","HR/Training and Recruitment","Hospitality/Tourism",
            "IT Hardware/Software","Legal","Logistics/Distribution","Management",
            "Manufacturing/Warehouse","Marketing/Media/Creative","Medical/Doctor/Nursing",
            "PR/Communications","Project Management","Quality Assurance",
            "Research and Development","Sales/Business Development","Science/Technology",
            "Skilled Trades / Construction","Strategy/Planning","Translation",
            "Voluntary Work","Writing/Editing","Other"};

    String[] industrySet ={preIndustry,"NA", "Accounting/Audit/Tax Services","Advertising/PR/Marketing",
            "Agriculture/Forestry/Fishing","Airlines/Aviation","Arts/Performing Arts",
            "Athletics/Sports","Automotive","Banking/Financial Services/Investments",
            "Biotechnology/Pharmaceuticals","Broadcasting/Music/Film",
            "Chemicals/Petroleum/Chemicals","Clothing/Garment/Textile",
            "Construction/Building/Architecture","Consulting/Professional Services",
            "Education/Training","Electronics/Electrical Equipment",
            "Energy/Water/Oil & Gas/Waste","Engineering/Machinery","Entertainment",
            "FMCG","Food and Beverage/Catering","Government/Embassy/Armed Forces",
            "Healthcare/Beauty Care","Hospitality/Hotels","Insurance/Pensions",
            "Interior Design/Decoration","Internet Services","IT/Computer",
            "Jewellery/Gems","Legal Services","Logistics/Transport","Manufacturing",
            "Media/Publishing","Medical/Hospital","Mining","NGO/UN/Non Profit",
            "Pharmaceutical/Life Sciences","Printing/Graphic Design",
            "Real Estate/Property Development","Recruitment/Employment Agency",
            "Retail/Wholesale","Security/Surveillance","Telecommunications",
            "Tourism/Travel Agency","Trading/Distribution/Import/Export","Other"};

    String[] religionSet = {preReligion,"NA","Buddhism", "Christian",
            "Islam","Hindu", "Atheist", "Other"};

    String[] raceSet = {preRace, "NA", "Kachin", "Kayar", "Kayin", "Chin", "Mon", "Bamar",
            "Rakhine", "Shan", "Indian", "Chinese", "Other"};

    String[] telcoSet = {preTelco, "MPT", "Ooredoo", "Telenor", "MEC", "Other"};


    String selectedCity ="";
    String _id;
    String password;
    String loadedCity="";
    String loadedTown="";
    String income ="";
    String education ="";

    int position =0;
    String val;

    ArrayAdapter<String> currentDiviAdapter;
    ArrayAdapter<String> currentTownAdapter;
    ArrayAdapter<String> incomeAdapter;
    ArrayAdapter<String> eduAdapter;
    ArrayAdapter<String> maritalAdapter;
    ArrayAdapter<String> empAdapter;
    ArrayAdapter<String> careerAdapter;
    ArrayAdapter<String> jobFuncAdapter;
    ArrayAdapter<String> industryAdapter;
    ArrayAdapter<String> religionAdapter;
    ArrayAdapter<String> raceAdapter;
    ArrayAdapter<String> telcoAdapter;

    String login_user;

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        dpDOB = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        txtDOB.setText(dateFormatter.format(newDate.getTime()));
                    }

                },
                newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_save) {
            createMeller();
        }

        return super.onOptionsItemSelected(item);
    }

    private void createMeller() {

        DBHelper dbH = new DBHelper(this);

        Meller m = new Meller();

        EditText txtMobileNumber = (EditText)findViewById(R.id.txtMobileNumber);
        EditText txtName = (EditText)findViewById(R.id.txtName);
        RadioButton rdMale = (RadioButton)findViewById(R.id.rdMale);
        RadioButton rdFemale = (RadioButton)findViewById(R.id.rdFemale);
        Spinner spinnerState = (Spinner)findViewById(R.id.spinnerState);
        Spinner spinnerCity = (Spinner)findViewById(R.id.spinnerCity);
        TextView txtDOB = (TextView)findViewById(R.id.txtDOB);
        TextView txtNRIC = (TextView)findViewById(R.id.txtNRIC);
        Spinner spinnerEducation = (Spinner)findViewById(R.id.spinnerEducation);
        Spinner spinnerIncome = (Spinner)findViewById(R.id.spinnerIncome);
        Spinner spinnerMarital = (Spinner)findViewById(R.id.spinnerMarital);
        Spinner spinnerEmp = (Spinner)findViewById(R.id.spinnerEmp);
        Spinner spinnerCareer = (Spinner)findViewById(R.id.spinnerCareer);
        Spinner spinnerJobfunc = (Spinner)findViewById(R.id.spinnerJobfunc);
        Spinner spinnerIndustry = (Spinner)findViewById(R.id.spinnerIndustry);
        Spinner spinnerTelco = (Spinner)findViewById(R.id.spinnerTelco);
        Spinner spinnerReligion = (Spinner)findViewById(R.id.spinnerReligion);
        Spinner spinnerRace = (Spinner)findViewById(R.id.spinnerRace);
        RadioButton radioBankYes = (RadioButton)findViewById(R.id.radioBankYes);
        RadioButton radioBankNo = (RadioButton)findViewById(R.id.radioBankNo);
        RadioButton radioSmokerYes = (RadioButton)findViewById(R.id.radioSmokerYes);
        RadioButton radioSmokerNo = (RadioButton)findViewById(R.id.radioSmokerNo);
        RadioButton radioDrinkerYes = (RadioButton)findViewById(R.id.radioDrinkerYes);
        RadioButton radioDrinkerNo = (RadioButton)findViewById(R.id.radioDrinkerNo);

        String strCurrentState = spinnerState.getSelectedItem()==null? preState :spinnerState.getSelectedItem().toString();
        if (strCurrentState.equals(preState)) {
            strCurrentState="";
        }

        String strCurrentCity = spinnerCity.getSelectedItem()==null? preCity :spinnerCity.getSelectedItem().toString();
        if (strCurrentCity.equals(preCity)) {
            strCurrentCity="";
        }

        String strEducation = spinnerEducation.getSelectedItem()==null? preEduc :spinnerEducation.getSelectedItem().toString();
        if (strEducation.equals(preEduc)) {
            strEducation="";
        }

        String strIncome = spinnerIncome.getSelectedItem()==null? preIncome :spinnerIncome.getSelectedItem().toString();
        if (strIncome.equals(preIncome)) {
            strIncome="";
        }

        String strMarital = spinnerMarital.getSelectedItem()==null? preMarital :spinnerMarital.getSelectedItem().toString();
        if (strMarital.equals(preEduc)) {
            strMarital="";
        }

        String strEmp = spinnerEmp.getSelectedItem()==null? preEmployment :spinnerEmp.getSelectedItem().toString();
        if (strEmp.equals(preEduc)) {
            strEmp="";
        }

        String strCareer = spinnerCareer.getSelectedItem()==null? preCareer :spinnerCareer.getSelectedItem().toString();
        if (strCareer.equals(preCareer)) {
            strCareer="";
        }

        String strJobfunc = spinnerJobfunc.getSelectedItem()==null? preJobFunc :spinnerJobfunc.getSelectedItem().toString();
        if (strJobfunc.equals(preJobFunc)) {
            strJobfunc = "";
        }

        String strIndustry = spinnerIndustry.getSelectedItem()==null? preIndustry :spinnerIndustry.getSelectedItem().toString();
        if (strIndustry.equals(preIndustry)) {
            strIndustry ="";
        }

        String strTelco = spinnerTelco.getSelectedItem()==null? preTelco :spinnerTelco.getSelectedItem().toString();
        if (strTelco.equals(preTelco)) {
            strTelco="";
        }

        String strReligion = spinnerReligion.getSelectedItem()==null? preRace :spinnerReligion.getSelectedItem().toString();
        if (strReligion.equals(preRace)) {
            strReligion = "";
        }

        String strRace = spinnerRace.getSelectedItem()==null? preRace :spinnerRace.getSelectedItem().toString();
        if (strRace.equals(preRace)) {
            strRace ="";
        }

        m.setPhone(txtMobileNumber.getText().toString());
        m.setFullName(txtName.getText().toString());

        if (rdMale.isChecked()) {
            m.setGender("male");
        }
        else if (rdFemale.isChecked()) {
            m.setGender("female");
        }

        if (radioBankYes.isChecked()) {
            m.setBankAccount("yes");
        }
        else if (radioBankNo.isChecked()) {
            m.setBankAccount("no");
        }
        else {
            m.setBankAccount("NA");
        }

        if (radioSmokerNo.isChecked()) {
            m.setSmoker("no");
        }
        else if (radioSmokerYes.isChecked()) {
            m.setSmoker("yes");
        }
        else {
            m.setSmoker("NA");
        }

        if (radioDrinkerNo.isChecked()) {
            m.setDrinker("no");
        }
        else if (radioDrinkerYes.isChecked()) {
            m.setDrinker("yes");
        }
        else {
            m.setDrinker("NA");
        }

        m.setCurrentCity(strCurrentState + "|" + strCurrentCity);
        m.setDob(UtilityHelper.getDateTime(txtDOB.getText().toString(), true));
        m.setNric(txtNRIC.getText().toString());
        m.setEducation(strEducation);
        m.setIncome(strIncome);
        m.setMarital(strMarital);
        m.setEmployment(strEmp);
        m.setCareer(strCareer);
        m.setJobFunction(strJobfunc);
        m.setIndustry(strIndustry);
        m.setTelco(strTelco);
        m.setReligion(strReligion);
        m.setRace(strRace);
        m.setReferrer(AppValues.getInstance().loginUser.getUser_name());
        m.setStatus("unsync");

        try {
            dbH.createMeller(m);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this).setTitle("Cancel Meller?")
                .setMessage("Are you sure you want to cancel this new Meller?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Toolbar toolbar = (Toolbar) findViewById(R.id.create_toolbar);
        if (toolbar != null) {
            toolbar.setTitle(R.string.create_meller);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        setDateTimeField();
        dpDOB.updateDate(1980,1,1);
//        dpDOB.getDatePicker().setCalendarViewShown(false);

        TextView txtMobile = (TextView) findViewById(R.id.txtMobileNumber);
        txtMobile.setText(getIntent().getStringExtra(P_NUMBER));

        txtDOB = (TextView) findViewById(R.id.txtDOB);
        txtDOB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dpDOB.show();
            }
        });

//        Toolbar myToolbar = (Toolbar) findViewById(R.id.create_toolbar);
//        setSupportActionBar(myToolbar);

        final Spinner spinnerState = (Spinner) findViewById(R.id.spinnerState);

        final Spinner spinnerCity = (Spinner) findViewById(R.id.spinnerCity);

        currentTownAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,blackState);
        currentTownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(currentTownAdapter);

        currentDiviAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, citySet);
        currentDiviAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(currentDiviAdapter);

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = spinnerState.getSelectedItem().toString();
                switch (selectedCity) {
                    case "Yangon":
                        forTown(yangonDivision);
                        break;
                    case "Mandalay":
                        forTown(mandalayDivision);
                        break;
                    case "Sagaing":
                        forTown(sagaingDivision);
                        break;
                    case "Magway":
                        forTown(magwayDivision);
                        break;
                    case "Ayeyarwady":
                        forTown(ayeyarwadyDivision);
                        break;
                    case "Bago (East)":
                        forTown(bagoEastDivision);
                        break;
                    case "Bago (West)":
                        forTown(bagoWestDivision);
                        break;
                    case "Tanintharyi":
                        forTown(tanintharyiDivision);
                        break;
                    case "Kachin":
                        forTown(kachinState);
                        break;
                    case "Kayar":
                        forTown(kayarState);
                        break;
                    case "Kayin":
                        forTown(kayinState);
                        break;
                    case "Chin":
                        forTown(chinState);
                        break;
                    case "Mon":
                        forTown(monState);
                        break;
                    case "Rakhine":
                        forTown(rakhineState);
                        break;
                    case "Shan (South)":
                        forTown(shanSouthState);
                        break;
                    case "Shan (North)":
                        forTown(shanNorthState);
                        break;
                    case "Shan (East)":
                        forTown(shanEasteState);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Spinner spinnerIncome = (Spinner) findViewById(R.id.spinnerIncome);
        final Spinner spinnerEdu = (Spinner) findViewById(R.id.spinnerEducation);
        final Spinner spinnerMarital = (Spinner) findViewById(R.id.spinnerMarital);
        final Spinner spinnerEmp = (Spinner) findViewById(R.id.spinnerEmp);
        final Spinner spinnerCareer = (Spinner) findViewById(R.id.spinnerCareer);
        final Spinner spinnerJobfunc = (Spinner) findViewById(R.id.spinnerJobfunc);
        final Spinner spinnerIndustry = (Spinner) findViewById(R.id.spinnerIndustry);
        final Spinner spinnerTelco = (Spinner) findViewById(R.id.spinnerTelco);
        final Spinner spinnerReligion = (Spinner) findViewById(R.id.spinnerReligion);
        final Spinner spinnerRace = (Spinner) findViewById(R.id.spinnerRace);

        incomeAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,incomeSet);
        incomeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIncome.setAdapter(incomeAdapter);

        eduAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,educationSet);
        eduAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdu.setAdapter(eduAdapter);

        maritalAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                maritalSet);
        maritalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMarital.setAdapter(maritalAdapter);

        empAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                empSet);
        empAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmp.setAdapter(empAdapter);

        careerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                careerSet);
        careerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCareer.setAdapter(careerAdapter);

        jobFuncAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                jobFuncSet);
        jobFuncAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJobfunc.setAdapter(jobFuncAdapter);

        industryAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                industrySet);
        industryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIndustry.setAdapter(industryAdapter);

        telcoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                telcoSet);
        telcoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTelco.setAdapter(telcoAdapter);

        religionAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                religionSet);
        religionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerReligion.setAdapter(religionAdapter);

        raceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                raceSet);
        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRace.setAdapter(raceAdapter);
    }

    public void forTown(String[]township){
        final Spinner spinnerCity = (Spinner) findViewById(R.id.spinnerCity);

        currentTownAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,township);
        currentTownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(currentTownAdapter);

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            protected Adapter initializedAdapter = null;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (initializedAdapter != parent.getAdapter()) {
                    initializedAdapter = parent.getAdapter();
                    return;
                }
                val = spinnerCity.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(!loadedTown.equals("")) {
            spinnerCity.post(new Runnable() {
                @Override
                public void run() {
                    int spinnerPosition = currentTownAdapter.getPosition(loadedTown);
                    spinnerCity.setSelection(spinnerPosition);
                }
            });
        }
    }
}
