package com.example.a2025_st42_r01_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        TextView txt = view.findViewById(R.id.txtViewFirst);
        txt.setText(mParam1);
        if (mParam1 == null){
            txt.setText("Fragment First");
        }

        Button btnFirst = view.findViewById(R.id.btnFirst);
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = requireActivity().getSupportFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();
                EditText ed = view.findViewById(R.id.etTextFirst);
                String str = ed.getText().toString();



//                SecondFragment fragment = new SecondFragment();
//                Bundle args = new Bundle();
//                args.putString("data", str);
//                fragment.setArguments(args);


                trans.replace(R.id.fragmentContainerView, SecondFragment.newInstance(str, ""));
                trans.commit();
                toggleButtons();
            }
            private void toggleButtons() {
                Button btnFirst = requireActivity().findViewById(R.id.btnMain);
                Button btnSecond = requireActivity().findViewById(R.id.btnSecond);
                btnFirst.setEnabled(false);
                btnFirst.setAlpha(0.5f);
                btnSecond.setEnabled(true);
                btnSecond.setAlpha(1.0f);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}