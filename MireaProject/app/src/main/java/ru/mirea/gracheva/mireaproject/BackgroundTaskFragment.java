package ru.mirea.gracheva.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import ru.mirea.gracheva.mireaproject.databinding.FragmentBackgroundTaskBinding; // замените на ваш пакет

public class BackgroundTaskFragment extends Fragment {
    private FragmentBackgroundTaskBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBackgroundTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnStartTask.setOnClickListener(v -> {
            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.UNMETERED)
                    .setRequiresCharging(true)
                    .build();

            OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyBackgroundWorker.class)
                    .setConstraints(constraints)
                    .build();

            WorkManager.getInstance(requireContext()).enqueue(workRequest);

            binding.tvStatus.setText("Задача поставлена в очередь. Выполнится, когда будут условия (Wi-Fi + зарядка)");
            Toast.makeText(getContext(), "Задача запущена", Toast.LENGTH_SHORT).show();

            WorkManager.getInstance(requireContext())
                    .getWorkInfoByIdLiveData(workRequest.getId())
                    .observe(getViewLifecycleOwner(), workInfo -> {
                        if (workInfo != null) {
                            if (workInfo.getState().isFinished()) {
                                binding.tvStatus.setText("Задача завершена. Успех: " + workInfo.getState().equals(WorkInfo.State.SUCCEEDED));
                            } else {
                                binding.tvStatus.setText("Состояние: " + workInfo.getState().name());
                            }
                        }
                    });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}