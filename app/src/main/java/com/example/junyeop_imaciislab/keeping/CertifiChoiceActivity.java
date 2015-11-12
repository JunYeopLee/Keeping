package com.example.junyeop_imaciislab.keeping;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.junyeop_imaciislab.keeping.adapter.CertifiChoiceListAdapter;
import com.example.junyeop_imaciislab.keeping.adapter.sortSpinnerAdapter;
import com.example.junyeop_imaciislab.keeping.util.inventoryListDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CertifiChoiceActivity extends Activity {
    public Spinner sortSpinner;
    private Context context;
    private com.example.junyeop_imaciislab.keeping.adapter.sortSpinnerAdapter sortSpinnerAdapter;
    private ArrayList<String> spinnerArrayList = new ArrayList<>();

    private inventoryListDAO inventoryListDAO;
    private CertifiChoiceListAdapter certifiChoiceListAdapter;
    private ListView inventoryListview;
    private ArrayList<inventoryListDAO> inventoryListDAOArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifi_choice);
        context = this;
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resume();
    }

    private void init() {
        sortSpinner = (Spinner)findViewById(R.id.spinner_sort);
        spinnerArrayList.add("donationDateDesc");
        spinnerArrayList.add("donationDateAsc");
        spinnerArrayList.add("givenCard");
        spinnerArrayList.add("myCard");
        sortSpinnerAdapter = new sortSpinnerAdapter(this,spinnerArrayList);
        sortSpinner.setAdapter(sortSpinnerAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                inventoryListview = (ListView) findViewById(R.id.listview_cardlist);
                if (i == 0) {
                    Collections.sort(inventoryListDAOArrayList, new Comparator<inventoryListDAO>() {
                        public int compare(inventoryListDAO s1, inventoryListDAO s2) {
                            return s2.getDonationDate().compareTo(s1.getDonationDate());
                        }
                    });
                    certifiChoiceListAdapter = new CertifiChoiceListAdapter((Activity) context, inventoryListDAOArrayList);
                    inventoryListview.setAdapter(certifiChoiceListAdapter);
                } else if (i == 1) {
                    Collections.sort(inventoryListDAOArrayList, new Comparator<inventoryListDAO>() {
                        public int compare(inventoryListDAO s1, inventoryListDAO s2) {
                            return s1.getDonationDate().compareTo(s2.getDonationDate());
                        }
                    });
                    certifiChoiceListAdapter = new CertifiChoiceListAdapter((Activity) context, inventoryListDAOArrayList);
                    inventoryListview.setAdapter(certifiChoiceListAdapter);
                } else if (i == 2) {
                    ArrayList<inventoryListDAO> newArrayList = new ArrayList<>();
                    for (int j = 0; j < inventoryListDAOArrayList.size(); j++) {
                        if (inventoryListDAOArrayList.get(j).getIsGiven())
                            newArrayList.add(inventoryListDAOArrayList.get(j));
                    }
                    certifiChoiceListAdapter = new CertifiChoiceListAdapter((Activity) context, newArrayList);
                    inventoryListview.setAdapter(certifiChoiceListAdapter);
                } else if (i == 3) {
                    ArrayList<inventoryListDAO> newArrayList = new ArrayList<>();
                    for (int j = 0; j < inventoryListDAOArrayList.size(); j++) {
                        if (!inventoryListDAOArrayList.get(j).getIsGiven())
                            newArrayList.add(inventoryListDAOArrayList.get(j));
                    }
                    certifiChoiceListAdapter = new CertifiChoiceListAdapter((Activity) context, newArrayList);
                    inventoryListview.setAdapter(certifiChoiceListAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("NOTSPINNER", adapterView.toString());
            }
        });
    }

    private void resume() {

        inventoryListDAOArrayList = new ArrayList<>();

        inventoryListDAO = new inventoryListDAO();
        inventoryListDAO.setCardNumber("11111111");
        inventoryListDAO.setName("이준엽");
        inventoryListDAO.setId("duql2072");
        inventoryListDAO.setBirthDay("1991.09.03");
        inventoryListDAO.setDonationCategory("전혈 400mL");
        inventoryListDAO.setDonationDate("2014.02.21");
        inventoryListDAO.setGender("남");
        inventoryListDAO.setDonationLocation("창원 혈액원");
        inventoryListDAO.setIsGiven(true);
        inventoryListDAO.setMessage("창원에서왔어");
        inventoryListDAOArrayList.add(inventoryListDAO);

        inventoryListDAO = new inventoryListDAO();
        inventoryListDAO.setCardNumber("22222222");
        inventoryListDAO.setName("김도연");
        inventoryListDAO.setId("duql2072");
        inventoryListDAO.setBirthDay("1988.09.03");
        inventoryListDAO.setDonationCategory("전혈 500mL");
        inventoryListDAO.setDonationDate("2013.02.21");
        inventoryListDAO.setGender("남");
        inventoryListDAO.setDonationLocation("대구 혈액원");
        inventoryListDAO.setIsGiven(false);
        inventoryListDAO.setMessage("대구에서왔어");
        inventoryListDAOArrayList.add(inventoryListDAO);

        inventoryListDAO = new inventoryListDAO();
        inventoryListDAO.setCardNumber("33333333");
        inventoryListDAO.setName("김주희");
        inventoryListDAO.setId("duql2072");
        inventoryListDAO.setBirthDay("1989.09.03");
        inventoryListDAO.setDonationCategory("전혈 600mL");
        inventoryListDAO.setDonationDate("2012.02.21");
        inventoryListDAO.setGender("여");
        inventoryListDAO.setDonationLocation("서울 혈액원");
        inventoryListDAO.setIsGiven(true);
        inventoryListDAO.setMessage("서울에서왔어");
        inventoryListDAOArrayList.add(inventoryListDAO);

        inventoryListDAO = new inventoryListDAO();
        inventoryListDAO.setCardNumber("44444444");
        inventoryListDAO.setName("장두수");
        inventoryListDAO.setId("duql2072");
        inventoryListDAO.setBirthDay("1990.09.03");
        inventoryListDAO.setDonationCategory("전혈 700mL");
        inventoryListDAO.setDonationDate("2011.02.21");
        inventoryListDAO.setGender("남");
        inventoryListDAO.setDonationLocation("서울 혈액원");
        inventoryListDAO.setIsGiven(false);
        inventoryListDAO.setMessage("서울에서왔어");
        inventoryListDAOArrayList.add(inventoryListDAO);

        inventoryListDAO = new inventoryListDAO();
        inventoryListDAO.setCardNumber("55555555");
        inventoryListDAO.setName("김준혁");
        inventoryListDAO.setId("duql2072");
        inventoryListDAO.setBirthDay("1992.09.03");
        inventoryListDAO.setDonationCategory("전혈 800mL");
        inventoryListDAO.setDonationDate("2009.02.21");
        inventoryListDAO.setGender("남");
        inventoryListDAO.setDonationLocation("구미 혈액원");
        inventoryListDAO.setIsGiven(true);
        inventoryListDAO.setMessage("구에미서왔어");
        inventoryListDAOArrayList.add(inventoryListDAO);

        certifiChoiceListAdapter = new CertifiChoiceListAdapter(this,inventoryListDAOArrayList);
        inventoryListview = (ListView)findViewById(R.id.listview_cardlist);
        inventoryListview.setAdapter(certifiChoiceListAdapter);
    }
}