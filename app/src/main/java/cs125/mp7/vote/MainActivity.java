package cs125.mp7.vote;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static String address = "none";

    private static RequestQueue requestQueue;

    private static String jsonInformationTemp = "";

    private static String jsonInformation = "{\n" +
            " \"kind\": \"civicinfo#representativeInfoResponse\",\n" +
            " \"normalizedInput\": {\n" +
            "  \"line1\": \"1012 West Illinois Street\",\n" +
            "  \"city\": \"Chicago\",\n" +
            "  \"state\": \"IL\",\n" +
            "  \"zip\": \"60654\"\n" +
            " },\n" +
            " \"divisions\": {\n" +
            "  \"ocd-division/country:us\": {\n" +
            "   \"name\": \"United States\",\n" +
            "   \"officeIndices\": [\n" +
            "    0,\n" +
            "    1\n" +
            "   ]\n" +
            "  },\n" +
            "  \"ocd-division/country:us/state:il\": {\n" +
            "   \"name\": \"Illinois\",\n" +
            "   \"officeIndices\": [\n" +
            "    2,\n" +
            "    4,\n" +
            "    5,\n" +
            "    9,\n" +
            "    10,\n" +
            "    11,\n" +
            "    12\n" +
            "   ]\n" +
            "  },\n" +
            "  \"ocd-division/country:us/state:il/cd:7\": {\n" +
            "   \"name\": \"Illinois's 7th congressional district\",\n" +
            "   \"officeIndices\": [\n" +
            "    3\n" +
            "   ]\n" +
            "  },\n" +
            "  \"ocd-division/country:us/state:il/county:cook\": {\n" +
            "   \"name\": \"Cook County\",\n" +
            "   \"officeIndices\": [\n" +
            "    13,\n" +
            "    14,\n" +
            "    15,\n" +
            "    16,\n" +
            "    17,\n" +
            "    18,\n" +
            "    19,\n" +
            "    20,\n" +
            "    21\n" +
            "   ]\n" +
            "  },\n" +
            "  \"ocd-division/country:us/state:il/place:chicago\": {\n" +
            "   \"name\": \"Chicago city\",\n" +
            "   \"officeIndices\": [\n" +
            "    6,\n" +
            "    7,\n" +
            "    8\n" +
            "   ]\n" +
            "  }\n" +
            " },\n" +
            " \"offices\": [\n" +
            "  {\n" +
            "   \"name\": \"President of the United States\",\n" +
            "   \"divisionId\": \"ocd-division/country:us\",\n" +
            "   \"levels\": [\n" +
            "    \"country\"\n" +
            "   ],\n" +
            "   \"roles\": [\n" +
            "    \"headOfState\",\n" +
            "    \"headOfGovernment\"\n" +
            "   ],\n" +
            "   \"officialIndices\": [\n" +
            "    0\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Vice-President of the United States\",\n" +
            "   \"divisionId\": \"ocd-division/country:us\",\n" +
            "   \"levels\": [\n" +
            "    \"country\"\n" +
            "   ],\n" +
            "   \"roles\": [\n" +
            "    \"deputyHeadOfGovernment\"\n" +
            "   ],\n" +
            "   \"officialIndices\": [\n" +
            "    1\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"United States Senate\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il\",\n" +
            "   \"levels\": [\n" +
            "    \"country\"\n" +
            "   ],\n" +
            "   \"roles\": [\n" +
            "    \"legislatorUpperBody\"\n" +
            "   ],\n" +
            "   \"officialIndices\": [\n" +
            "    2,\n" +
            "    3\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"United States House of Representatives IL-07\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il/cd:7\",\n" +
            "   \"levels\": [\n" +
            "    \"country\"\n" +
            "   ],\n" +
            "   \"roles\": [\n" +
            "    \"legislatorLowerBody\"\n" +
            "   ],\n" +
            "   \"officialIndices\": [\n" +
            "    4\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Governor\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il\",\n" +
            "   \"levels\": [\n" +
            "    \"administrativeArea1\"\n" +
            "   ],\n" +
            "   \"roles\": [\n" +
            "    \"headOfGovernment\"\n" +
            "   ],\n" +
            "   \"officialIndices\": [\n" +
            "    5\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Lieutenant Governor\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il\",\n" +
            "   \"levels\": [\n" +
            "    \"administrativeArea1\"\n" +
            "   ],\n" +
            "   \"roles\": [\n" +
            "    \"deputyHeadOfGovernment\"\n" +
            "   ],\n" +
            "   \"officialIndices\": [\n" +
            "    6\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Mayor\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il/place:chicago\",\n" +
            "   \"officialIndices\": [\n" +
            "    7\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"City Clerk\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il/place:chicago\",\n" +
            "   \"officialIndices\": [\n" +
            "    8\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"City Treasurer\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il/place:chicago\",\n" +
            "   \"officialIndices\": [\n" +
            "    9\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"State Treasurer\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il\",\n" +
            "   \"officialIndices\": [\n" +
            "    10\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Attorney General\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il\",\n" +
            "   \"officialIndices\": [\n" +
            "    11\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"State Comptroller\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il\",\n" +
            "   \"officialIndices\": [\n" +
            "    12\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Secretary of State\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il\",\n" +
            "   \"officialIndices\": [\n" +
            "    13\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Sheriff\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il/county:cook\",\n" +
            "   \"officialIndices\": [\n" +
            "    14\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Assessor\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il/county:cook\",\n" +
            "   \"officialIndices\": [\n" +
            "    15\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Treasurer\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il/county:cook\",\n" +
            "   \"officialIndices\": [\n" +
            "    16\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"County Clerk\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il/county:cook\",\n" +
            "   \"officialIndices\": [\n" +
            "    17\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Circuit Clerk\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il/county:cook\",\n" +
            "   \"officialIndices\": [\n" +
            "    18\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"State's Attorney\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il/county:cook\",\n" +
            "   \"officialIndices\": [\n" +
            "    19\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Recorder of Deeds\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il/county:cook\",\n" +
            "   \"officialIndices\": [\n" +
            "    20\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Circuit Court Judge\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il/county:cook\",\n" +
            "   \"officialIndices\": [\n" +
            "    21,\n" +
            "    22,\n" +
            "    23,\n" +
            "    24,\n" +
            "    25,\n" +
            "    26,\n" +
            "    27,\n" +
            "    28,\n" +
            "    29,\n" +
            "    30,\n" +
            "    31\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"County Board President\",\n" +
            "   \"divisionId\": \"ocd-division/country:us/state:il/county:cook\",\n" +
            "   \"officialIndices\": [\n" +
            "    32\n" +
            "   ]\n" +
            "  }\n" +
            " ],\n" +
            " \"officials\": [\n" +
            "  {\n" +
            "   \"name\": \"Donald J. Trump\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"The White House\",\n" +
            "     \"line2\": \"1600 Pennsylvania Avenue NW\",\n" +
            "     \"city\": \"Washington\",\n" +
            "     \"state\": \"DC\",\n" +
            "     \"zip\": \"20500\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Republican\",\n" +
            "   \"phones\": [\n" +
            "    \"(202) 456-1111\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.whitehouse.gov/\"\n" +
            "   ],\n" +
            "   \"photoUrl\": \"https://www.whitehouse.gov/sites/whitehouse.gov/files/images/45/PE%20Color.jpg\",\n" +
            "   \"channels\": [\n" +
            "    {\n" +
            "     \"type\": \"GooglePlus\",\n" +
            "     \"id\": \"+whitehouse\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"Facebook\",\n" +
            "     \"id\": \"whitehouse\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"Twitter\",\n" +
            "     \"id\": \"potus\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"YouTube\",\n" +
            "     \"id\": \"whitehouse\"\n" +
            "    }\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Mike Pence\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"The White House\",\n" +
            "     \"line2\": \"1600 Pennsylvania Avenue NW\",\n" +
            "     \"city\": \"Washington\",\n" +
            "     \"state\": \"DC\",\n" +
            "     \"zip\": \"20500\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Republican\",\n" +
            "   \"phones\": [\n" +
            "    \"(202) 456-1111\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.whitehouse.gov/\"\n" +
            "   ],\n" +
            "   \"photoUrl\": \"https://www.whitehouse.gov/sites/whitehouse.gov/files/images/45/VPE%20Color.jpg\",\n" +
            "   \"channels\": [\n" +
            "    {\n" +
            "     \"type\": \"GooglePlus\",\n" +
            "     \"id\": \"+whitehouse\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"Facebook\",\n" +
            "     \"id\": \"whitehouse\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"Twitter\",\n" +
            "     \"id\": \"VP\"\n" +
            "    }\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Tammy Duckworth\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"524 Hart Senate Office Building\",\n" +
            "     \"city\": \"Washington\",\n" +
            "     \"state\": \"DC\",\n" +
            "     \"zip\": \"20510\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Democratic\",\n" +
            "   \"phones\": [\n" +
            "    \"(202) 224-2854\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"https://www.duckworth.senate.gov\"\n" +
            "   ],\n" +
            "   \"photoUrl\": \"http://bioguide.congress.gov/bioguide/photo/D/D000622.jpg\",\n" +
            "   \"channels\": [\n" +
            "    {\n" +
            "     \"type\": \"Facebook\",\n" +
            "     \"id\": \"SenDuckworth\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"Twitter\",\n" +
            "     \"id\": \"SenDuckworth\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"YouTube\",\n" +
            "     \"id\": \"SenDuckworth\"\n" +
            "    }\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Richard J. Durbin\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"711 Hart Senate Office Building\",\n" +
            "     \"city\": \"Washington\",\n" +
            "     \"state\": \"DC\",\n" +
            "     \"zip\": \"20510\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Democratic\",\n" +
            "   \"phones\": [\n" +
            "    \"(202) 224-2152\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.durbin.senate.gov/public/\"\n" +
            "   ],\n" +
            "   \"photoUrl\": \"http://bioguide.congress.gov/bioguide/photo/D/D000563.jpg\",\n" +
            "   \"channels\": [\n" +
            "    {\n" +
            "     \"type\": \"Facebook\",\n" +
            "     \"id\": \"SenatorDurbin\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"Twitter\",\n" +
            "     \"id\": \"SenatorDurbin\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"YouTube\",\n" +
            "     \"id\": \"SenatorDurbin\"\n" +
            "    }\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Danny K. Davis\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"2159 Rayburn House Office Building\",\n" +
            "     \"city\": \"Washington\",\n" +
            "     \"state\": \"DC\",\n" +
            "     \"zip\": \"20515\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Democratic\",\n" +
            "   \"phones\": [\n" +
            "    \"(202) 225-5006\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.davis.house.gov/\"\n" +
            "   ],\n" +
            "   \"photoUrl\": \"http://bioguide.congress.gov/bioguide/photo/D/D000096.jpg\",\n" +
            "   \"emails\": [\n" +
            "    \"davisk@mail.house.gov\"\n" +
            "   ],\n" +
            "   \"channels\": [\n" +
            "    {\n" +
            "     \"type\": \"Facebook\",\n" +
            "     \"id\": \"CongressmanDKDavis\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"Twitter\",\n" +
            "     \"id\": \"RepDannyDavis\"\n" +
            "    }\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Bruce Rauner\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"Office of the Governor 207 State House\",\n" +
            "     \"city\": \"Springfield\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"62706\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Republican\",\n" +
            "   \"phones\": [\n" +
            "    \"(217) 782-0244\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"https://www.illinois.gov/gov/pages/default.aspx\"\n" +
            "   ],\n" +
            "   \"photoUrl\": \"http://www.illinois.gov/gov/SiteCollectionImages/GovRauner.jpg\",\n" +
            "   \"channels\": [\n" +
            "    {\n" +
            "     \"type\": \"Facebook\",\n" +
            "     \"id\": \"GovRauner\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"Twitter\",\n" +
            "     \"id\": \"GovRauner\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"YouTube\",\n" +
            "     \"id\": \"brucerauner\"\n" +
            "    }\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Evelyn Sanguinetti\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"Office of the Lt. Governor 214 State House\",\n" +
            "     \"city\": \"Springfield\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"62706\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Republican\",\n" +
            "   \"phones\": [\n" +
            "    \"(217) 558-3085\"\n" +
            "   ],\n" +
            "   \"photoUrl\": \"http://www.illinois.gov/ltg/about/PublishingImages/Lt-Governor.jpg\",\n" +
            "   \"channels\": [\n" +
            "    {\n" +
            "     \"type\": \"Facebook\",\n" +
            "     \"id\": \"1558829541025779\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"Twitter\",\n" +
            "     \"id\": \"LtGovEvelyn\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"YouTube\",\n" +
            "     \"id\": \"UCDvzxyb1vjgrHeLUka7oEKw\"\n" +
            "    }\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Rahm Emanuel\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"City Hall 4th Floor Chicago\",\n" +
            "     \"line2\": \"IL 60602\",\n" +
            "     \"line3\": \"121 N LaSalle Street\",\n" +
            "     \"city\": \"Chicago\",\n" +
            "     \"state\": \"\",\n" +
            "     \"zip\": \"\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Democrat\",\n" +
            "   \"phones\": [\n" +
            "    \"(312) 744-5000\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.cityofchicago.org/city/en/depts/mayor.html\"\n" +
            "   ],\n" +
            "   \"photoUrl\": \"http://www.cityofchicago.org/content/dam/city/depts/mayor/general/Rahm_200x200.jpg\",\n" +
            "   \"channels\": [\n" +
            "    {\n" +
            "     \"type\": \"Twitter\",\n" +
            "     \"id\": \"RahmEmanuel\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"YouTube\",\n" +
            "     \"id\": \"ChicagoMayorsOffice\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"Twitter\",\n" +
            "     \"id\": \"ChicagosMayor\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"Facebook\",\n" +
            "     \"id\": \"rahmemanuel\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"GooglePlus\",\n" +
            "     \"id\": \"+chicagosmayor\"\n" +
            "    }\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Anna Valencia\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"121 N LaSalle Street\",\n" +
            "     \"city\": \"Chicago\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"60602\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"phones\": [\n" +
            "    \"(312) 742-5375\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://chicityclerk.com/\"\n" +
            "   ],\n" +
            "   \"emails\": [\n" +
            "    \"cityclerk@cityofchicago.org\"\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Kurt Summers\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"121 North LaSalle Street City Hall\",\n" +
            "     \"line2\": \"Room 106\",\n" +
            "     \"city\": \"Chicago\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"60602\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"phones\": [\n" +
            "    \"(312) 744-3356\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.chicagocitytreasurer.com/\"\n" +
            "   ],\n" +
            "   \"emails\": [\n" +
            "    \"citytreasurer@cityofchicago.org\"\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Michael W. Frerichs\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"Capitol Building 219 Statehouse\",\n" +
            "     \"city\": \"Springfield\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"62706\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Democratic\",\n" +
            "   \"phones\": [\n" +
            "    \"(312) 814-1700\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.treasurer.il.gov/\"\n" +
            "   ],\n" +
            "   \"channels\": [\n" +
            "    {\n" +
            "     \"type\": \"Facebook\",\n" +
            "     \"id\": \"TreasurerMichaelFrerichs\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"type\": \"Twitter\",\n" +
            "     \"id\": \"ILTreasurer\"\n" +
            "    }\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Lisa Madigan\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"500 South Second Street\",\n" +
            "     \"city\": \"Springfield\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"62706\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Democratic\",\n" +
            "   \"phones\": [\n" +
            "    \"(217) 782-1090\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.illinoisattorneygeneral.gov/\"\n" +
            "   ],\n" +
            "   \"channels\": [\n" +
            "    {\n" +
            "     \"type\": \"Twitter\",\n" +
            "     \"id\": \"ILattygeneral\"\n" +
            "    }\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Susana Mendoza\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"325 West Adams\",\n" +
            "     \"city\": \"Springfield\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"62704\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Democratic\",\n" +
            "   \"phones\": [\n" +
            "    \"(217) 782-6000\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.ioc.state.il.us/\"\n" +
            "   ],\n" +
            "   \"emails\": [\n" +
            "    \"comptroller@mail.ioc.state.il.us\"\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Jesse White\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"213 State Capitol\",\n" +
            "     \"city\": \"Springfield\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"62756\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Democratic\",\n" +
            "   \"phones\": [\n" +
            "    \"(800) 252-8980\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.cyberdriveillinois.com/\"\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Thomas Dart\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"50 W. Washington\",\n" +
            "     \"line2\": \"Room 704\",\n" +
            "     \"city\": \"Chicago\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"60602\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Unknown\",\n" +
            "   \"phones\": [\n" +
            "    \"(312) 603-6444\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.cookcountysheriff.org/\"\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Joseph Berrios\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"118 N. Clark St.\",\n" +
            "     \"line2\": \"Room 320\",\n" +
            "     \"city\": \"Chicago\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"60602\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Unknown\",\n" +
            "   \"phones\": [\n" +
            "    \"(312) 443-7550\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.cookcountyassessor.com/\"\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Maria Pappas\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"118 N. Clark Street,\",\n" +
            "     \"city\": \"Chicago\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"60602\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Unknown\",\n" +
            "   \"phones\": [\n" +
            "    \"(312) 443-5100\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.cookcountytreasurer.com/\"\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"David Orr\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"69 W. Washington\",\n" +
            "     \"line2\": \"Suite 500\",\n" +
            "     \"city\": \"Chicago\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"60602\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Unknown\",\n" +
            "   \"phones\": [\n" +
            "    \"(312) 603-5656\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"https://www.cookcountyil.gov/person/david-orr\"\n" +
            "   ],\n" +
            "   \"emails\": [\n" +
            "    \"D.Orr@cookcountyil.gov\"\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Dorothy Anne Brown\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"50 W Washington St\",\n" +
            "     \"line2\": \"Room 1001\",\n" +
            "     \"city\": \"Chicago\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"60602\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Democratic\",\n" +
            "   \"phones\": [\n" +
            "    \"(312) 603-5030\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"http://www.cookcountyclerkofcourt.org/\"\n" +
            "   ],\n" +
            "   \"emails\": [\n" +
            "    \"courtclerk@cookcountycourt.com\"\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Kimberly M. Foxx\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"69 W. Washington\",\n" +
            "     \"line2\": \"Suite 3200\",\n" +
            "     \"city\": \"Chicago\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"60602\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Democratic\",\n" +
            "   \"phones\": [\n" +
            "    \"(312) 603-1880\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"https://www.cookcountyil.gov/person/anita-alvarez\"\n" +
            "   ],\n" +
            "   \"emails\": [\n" +
            "    \"statesattorney@cookcountyil.gov\"\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Karen A. Yarbrough\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"118 N. Clark Street,\",\n" +
            "     \"city\": \"Chicago\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"60602\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Unknown\",\n" +
            "   \"phones\": [\n" +
            "    \"(312) 603-5050\"\n" +
            "   ]\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Carolyn J. Gallagher\",\n" +
            "   \"party\": \"Democratic\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Aleksandra \\\"Alex\\\" Gillespie\",\n" +
            "   \"party\": \"Democratic\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Susana L. Ortiz\",\n" +
            "   \"party\": \"Democratic\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Brendan A. O'Brien\",\n" +
            "   \"party\": \"Democratic\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Alison C. Conlon\",\n" +
            "   \"party\": \"Democratic\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Mary Kathleen Mchugh\",\n" +
            "   \"party\": \"Democratic\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"John Fitzgerald Lyke, Jr.\",\n" +
            "   \"party\": \"Democratic\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Maureen O'Donoghue Hannon\",\n" +
            "   \"party\": \"Democratic\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Patrick Joseph Powers\",\n" +
            "   \"party\": \"Democratic\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Rossana Patricia Fernandez\",\n" +
            "   \"party\": \"Democratic\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Daniel Patrick Duffy\",\n" +
            "   \"party\": \"Democratic\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Toni Preckwinkle\",\n" +
            "   \"address\": [\n" +
            "    {\n" +
            "     \"line1\": \"118 N. Clark Street\",\n" +
            "     \"city\": \"Chicago\",\n" +
            "     \"state\": \"IL\",\n" +
            "     \"zip\": \"60602\"\n" +
            "    }\n" +
            "   ],\n" +
            "   \"party\": \"Unknown\",\n" +
            "   \"phones\": [\n" +
            "    \"(312) 603-6400\"\n" +
            "   ],\n" +
            "   \"urls\": [\n" +
            "    \"https://www.cookcountyil.gov/person/toni-preckwinkle\"\n" +
            "   ],\n" +
            "   \"photoUrl\": \"https://www.cookcountyil.gov/sites/default/files/toni_preckwinkle_president.jpg\",\n" +
            "   \"channels\": [\n" +
            "    {\n" +
            "     \"type\": \"Twitter\",\n" +
            "     \"id\": \"ToniPreckwinkle\"\n" +
            "    }\n" +
            "   ]\n" +
            "  }\n" +
            " ]\n" +
            "}";

    /**
     * Google Civic Information API key
     */
    private final String apiKey = BuildConfig.API_KEY;

    public static String getAddress() {
        return address;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting.");

        SectionsPageAdapter mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        final FloatingActionButton locationButton = findViewById(R.id.fab);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAlertAndRequest();
            }
        });

        Toast.makeText(MainActivity.this, "Choose address using location button -->", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate: We got dis far");
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new LocalTab(), "Candidates");
        adapter.addFragment(new LocationTab(), "Polling Location");
        viewPager.setAdapter(adapter);
    }

    private void makeAlertAndRequest() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Voter Address (Street, City, State)");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                address = input.getText().toString();
                Log.d(TAG, "onClick: address set to: " + address);
                Toast.makeText(MainActivity.this, "Address Set!", Toast.LENGTH_SHORT).show();
                startAPICall();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Log.d(TAG, "onClick: address entry cancelled");
            }
        });
        builder.show();
    }

    public static String getJsonInformation() {
        return jsonInformation;
    }

    public static String getParsedAddress(String address) {
        return address.replaceAll(" ", "%20");
    }

    public static void setJsonInformation(String jsonInformation) {
        MainActivity.jsonInformation = jsonInformation;
    }

    /**
     * Send the address to the API and attempt to get a JSON response.
     */
    void startAPICall() {
        String url = "https://www.googleapis.com/civicinfo/v2/representatives?address="
                + getParsedAddress(address)
                + "&key=" + apiKey;
        final android.content.Context context = getApplicationContext();
        final Toast toast = Toast.makeText(context,
                "Retrieving data . . .",
                Toast.LENGTH_SHORT);
        toast.show();
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());
                            setJsonInformation(response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            Toast toast2 = Toast.makeText(context,
                    "Failed.",
                    Toast.LENGTH_SHORT);
            toast2.show();
            e.printStackTrace();
        }
    }
}
