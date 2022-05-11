package com.atul.audioplayer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atul.audioplayer.MPPreferences;
import com.atul.audioplayer.R;
import com.atul.audioplayer.helper.ThemeHelper;
import com.atul.audioplayer.viewmodel.MainViewModel;
import com.atul.audioplayer.viewmodel.MainViewModelFactory;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private MainViewModel viewModel;
    private RecyclerView accentView;
    private boolean state;
    private LinearLayout chipLayout;
    private ImageView currentThemeMode;


    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity(), new MainViewModelFactory(requireActivity())).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        accentView = view.findViewById(R.id.accent_view);
        chipLayout = view.findViewById(R.id.chip_layout);
        currentThemeMode = view.findViewById(R.id.current_theme_mode);

        LinearLayout themeModeOption = view.findViewById(R.id.theme_mode_option);
        LinearLayout refreshOption = view.findViewById(R.id.refresh_options);

        state = MPPreferences.getAlbumRequest(requireActivity().getApplicationContext());

        setCurrentThemeMode();

        accentView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        themeModeOption.setOnClickListener(this);
        refreshOption.setOnClickListener(this);

        view.findViewById(R.id.night_chip).setOnClickListener(this);
        view.findViewById(R.id.light_chip).setOnClickListener(this);
        view.findViewById(R.id.auto_chip).setOnClickListener(this);

        return view;
    }


    private void setCurrentThemeMode() {
        int mode = MPPreferences.getThemeMode(requireActivity().getApplicationContext());

        if (mode == AppCompatDelegate.MODE_NIGHT_NO)
            currentThemeMode.setImageResource(R.drawable.ic_theme_mode_light);

        else if (mode == AppCompatDelegate.MODE_NIGHT_YES)
            currentThemeMode.setImageResource(R.drawable.ic_theme_mode_night);

        else
            currentThemeMode.setImageResource(R.drawable.ic_theme_mode_auto);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();


        if (id == R.id.theme_mode_option) {
            int mode = chipLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
            chipLayout.setVisibility(mode);
        } else if (id == R.id.night_chip)
            selectTheme(AppCompatDelegate.MODE_NIGHT_YES);

        else if (id == R.id.light_chip)
            selectTheme(AppCompatDelegate.MODE_NIGHT_NO);

        else if (id == R.id.auto_chip)
            selectTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        else if (id == R.id.refresh_options) {
            Toast.makeText(requireActivity(), "Looking ...", Toast.LENGTH_SHORT).show();
            ThemeHelper.applySettings(requireActivity());
        }
    }


    private void selectTheme(int theme) {
        AppCompatDelegate.setDefaultNightMode(theme);
        MPPreferences.storeThemeMode(requireActivity().getApplicationContext(), theme);
    }

    private void setAlbumRequest() {
        MPPreferences.storeAlbumRequest(requireActivity().getApplicationContext(), (!state));
        ThemeHelper.applySettings(getActivity());
    }
}