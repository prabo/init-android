package com.elzup.init.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elzup.init.MainActivity;
import com.elzup.init.R;
import com.elzup.init.databinding.FragmentMissionCreateBinding;
import com.elzup.init.managers.SessionStore;
import com.elzup.init.models.MissionEntity;
import com.elzup.init.models.SessionEntity;
import com.elzup.init.network.InitService;
import com.elzup.init.network.InitServiceGenerator;

public class MissionCreateFragment extends Fragment {
    public static final String TAG = MissionCreateFragment.class.getSimpleName();
    private FragmentMissionCreateBinding binding;
    private MissionEntity newMission;
    private MainActivity activity;
    private InitService initService;
    public boolean isSync;

    public MissionCreateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MissionCreateFragment.
     */
    public static MissionCreateFragment newInstance() {
        MissionCreateFragment fragment = new MissionCreateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.isSync = false;
        SessionEntity session = SessionStore.getSession();
        initService = InitServiceGenerator.createService(session.getAccessToken());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_mission_create, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Inflate the layout for this fragment
        activity = (MainActivity) getActivity();
        activity.setTitle("ミッション詳細");

        binding = FragmentMissionCreateBinding.bind(getView());
        // TODO: Dummy Factory
        newMission = new MissionEntity(0, "", "", 0, false);
        binding.setMission(newMission);
        binding.setFragment(this);

        SessionEntity session = SessionStore.getSession();
        InitService initService = InitServiceGenerator.createService(session.getAccessToken());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void onSubmitButtonClick(View view) {
        if (isSync) {
            return;
        }
        isSync = true;
        // initService.postMission()
    }
}
