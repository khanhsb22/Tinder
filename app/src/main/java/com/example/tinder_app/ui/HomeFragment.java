package com.example.tinder_app.ui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.TransitionAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tinder_app.R;
import com.example.tinder_app.databinding.FragmentHomeBinding;
import com.example.tinder_app.swipe.SwipeModel;
import com.example.tinder_app.swipe.SwipeViewModel;

public class HomeFragment extends Fragment {
    private View v;
    private FragmentHomeBinding binding;
    private static final String TAG = "HomeFragment";
    private Context context;
    private static int index = 0;

    public HomeFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        v = binding.getRoot();

        SwipeViewModel viewModel = new ViewModelProvider(this).get(SwipeViewModel.class);

        viewModel.getStream().observe(getViewLifecycleOwner(), new Observer<SwipeModel>() {
            @Override
            public void onChanged(SwipeModel model) {
                bindCard(model);
            }
        });

        binding.flSuperLike.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.flSuperLike.setBackgroundResource(R.drawable.blue_bgr);
                    binding.imvSuperLike.setImageResource(R.drawable.white_star);
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    binding.flSuperLike.setBackgroundResource(R.drawable.white_bgr);
                    binding.imvSuperLike.setImageResource(R.drawable.blue_star);
                    Toast.makeText(context, "SUPER LIKE!", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return false;
            }
        });

        binding.flNope.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.flNope.setBackgroundResource(R.drawable.pink_bgr);
                    binding.imvNope.setImageResource(R.drawable.close_white);
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    binding.flNope.setBackgroundResource(R.drawable.white_bgr_big);
                    binding.imvNope.setImageResource(R.drawable.close);
                    binding.motionLayout.setProgress(0f);
                    binding.motionLayout.setTransition(R.id.rest, R.id.like);
                    viewModel.swipe();
                    return false;
                }
                return false;
            }
        });

        binding.flLike.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.flLike.setBackgroundResource(R.drawable.green_bgr);
                    binding.imvLike.setImageResource(R.drawable.heart_white);
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    binding.flLike.setBackgroundResource(R.drawable.white_bgr_big);
                    binding.imvLike.setImageResource(R.drawable.heart);
                    binding.motionLayout.setProgress(0f);
                    binding.motionLayout.setTransition(R.id.rest, R.id.like);
                    viewModel.swipe();
                    return false;
                }
                return false;
            }
        });

        binding.motionLayout.setTransitionListener(new TransitionAdapter() {
            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                super.onTransitionCompleted(motionLayout, currentId);
                if (currentId == R.id.offScreenPass) {
                    binding.motionLayout.setProgress(0f);
                    binding.motionLayout.setTransition(R.id.rest, R.id.like);
                    viewModel.swipe();
                    binding.flNope.setBackgroundResource(R.drawable.white_bgr_big);
                    binding.imvNope.setImageResource(R.drawable.close);
                    binding.flLike.setBackgroundResource(R.drawable.white_bgr_big);
                    binding.imvLike.setImageResource(R.drawable.heart);
                    binding.flSuperLike.setBackgroundResource(R.drawable.white_bgr);
                    binding.imvSuperLike.setImageResource(R.drawable.blue_star);
                    Log.d(TAG, "NOPE");
                }
                if (currentId == R.id.offScreenLike) {
                    binding.motionLayout.setProgress(0f);
                    binding.motionLayout.setTransition(R.id.rest, R.id.like);
                    viewModel.swipe();
                    binding.flNope.setBackgroundResource(R.drawable.white_bgr_big);
                    binding.imvNope.setImageResource(R.drawable.close);
                    binding.flLike.setBackgroundResource(R.drawable.white_bgr_big);
                    binding.imvLike.setImageResource(R.drawable.heart);
                    binding.flSuperLike.setBackgroundResource(R.drawable.white_bgr);
                    binding.imvSuperLike.setImageResource(R.drawable.blue_star);
                    Log.d(TAG, "LIKE");
                }
                if (currentId == R.id.pass) {
                    binding.tvNope.setVisibility(View.VISIBLE);
                    binding.tvLike.setVisibility(View.GONE);
                    binding.flNope.setBackgroundResource(R.drawable.pink_bgr);
                    binding.imvNope.setImageResource(R.drawable.close_white);
                    binding.flSuperLike.setBackgroundResource(R.drawable.white_bgr);
                    binding.imvSuperLike.setImageResource(R.drawable.blue_star);
                    Log.d(TAG, "NOPE!");
                }
                if (currentId == R.id.like) {
                    binding.tvNope.setVisibility(View.GONE);
                    binding.tvLike.setVisibility(View.VISIBLE);
                    binding.flLike.setBackgroundResource(R.drawable.green_bgr);
                    binding.imvLike.setImageResource(R.drawable.heart_white);
                    binding.flSuperLike.setBackgroundResource(R.drawable.white_bgr);
                    binding.imvSuperLike.setImageResource(R.drawable.blue_star);
                    Log.d(TAG, "LIKE!");
                }
                if (currentId == R.id.rest) {
                    binding.flNope.setBackgroundResource(R.drawable.white_bgr_big);
                    binding.imvNope.setImageResource(R.drawable.close);
                    binding.flLike.setBackgroundResource(R.drawable.white_bgr_big);
                    binding.imvLike.setImageResource(R.drawable.heart);
                    binding.flSuperLike.setBackgroundResource(R.drawable.white_bgr);
                    binding.imvSuperLike.setImageResource(R.drawable.blue_star);
                }
            }
        });

        return v;
    }

    private void bindCard(SwipeModel model) {
        // Set infos
        binding.imvMemberTop.setImageResource(model.top.images.get(0));
        binding.tvNameTop.setText(model.top.name);
        binding.tvAgeTop.setText("" + model.top.age);
        binding.tvAddressTop.setText("Live in " + model.top.address);
        binding.tvDistanceTop.setText("Distance " + model.top.distance + " km");

        binding.imvMemberBottom.setImageResource(model.bottom.images.get(0));
        binding.tvNameBottom.setText(model.bottom.name);
        binding.tvAgeBottom.setText("" + model.bottom.age);
        binding.tvAddressBottom.setText("Live in " + model.bottom.address);
        binding.tvDistanceBottom.setText("Distance " + model.bottom.distance + " km");

        index = 0;

        binding.tvNope.setVisibility(View.GONE);
        binding.tvLike.setVisibility(View.GONE);

        // Init image views
        if (!model.top.images.isEmpty()) {
            binding.lnNumImagesTop.removeAllViews();
            for (int i = 0; i < model.top.images.size(); i++) {
                View v = new View(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                params.setMargins(convertDpToPixel(context, 3.0f), 0, 0, 0);
                v.setLayoutParams(params);
                v.getLayoutParams().height = 14;
                v.setBackgroundResource(R.drawable.none_select_bgr);
                v.setTag("vTop" + i);
                binding.lnNumImagesTop.addView(v);

                if (i == index) {
                    v.setBackgroundResource(R.drawable.select_bgr);
                }
            }
            binding.lnNumImagesTop.setWeightSum(model.top.images.size());
        }

        if (!model.bottom.images.isEmpty()) {
            binding.lnNumImagesBottom.removeAllViews();
            for (int i = 0; i < model.bottom.images.size(); i++) {
                View v = new View(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                params.setMargins(convertDpToPixel(context, 5.0f), 0, 0, 0);
                v.setLayoutParams(params);
                v.getLayoutParams().height = 14;
                v.setBackgroundResource(R.drawable.none_select_bgr);
                v.setTag("vBottom" + i);
                binding.lnNumImagesBottom.addView(v);

                if (i == index) {
                    v.setBackgroundResource(R.drawable.select_bgr);
                }
            }
            binding.lnNumImagesBottom.setWeightSum(model.bottom.images.size());
        }

        final boolean[] isLongClick = {false};
        binding.motionLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isLongClick[0] = true;
                return false;
            }
        });

        // Click event to change images
        binding.motionLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isLongClick[0] = false;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    binding.tvNope.setVisibility(View.GONE);
                    binding.tvLike.setVisibility(View.GONE);
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    if (x < 600 && y <= 1300 && y > 50 && !isLongClick[0]) {
                        // Left click
                        if (index > 0) {
                            index--;
                            for (int i = 0; i < model.top.images.size(); i++) {
                                if (i == index) {
                                    View view = binding.lnNumImagesTop.findViewWithTag("vTop" + i);
                                    view.setBackgroundResource(R.drawable.select_bgr);
                                    binding.imvMemberTop.setImageResource(model.top.images.get(i));
                                } else {
                                    View view = binding.lnNumImagesTop.findViewWithTag("vTop" + i);
                                    view.setBackgroundResource(R.drawable.none_select_bgr);
                                }
                            }
                        }
                    }
                    if (x > 600 && y <= 1300 && y > 50 && !isLongClick[0]) {
                        // Right click
                        if (index < model.top.images.size() - 1) {
                            index++;
                            for (int i = 0; i < model.top.images.size(); i++) {
                                if (i == index) {
                                    View view = binding.lnNumImagesTop.findViewWithTag("vTop" + i);
                                    view.setBackgroundResource(R.drawable.select_bgr);
                                    binding.imvMemberTop.setImageResource(model.top.images.get(i));
                                } else {
                                    View view = binding.lnNumImagesTop.findViewWithTag("vTop" + i);
                                    view.setBackgroundResource(R.drawable.none_select_bgr);
                                }
                            }
                        }
                    }
                    Log.d(TAG, "onTouch: x: " + x + "|y:" + y);
                }
                return false;
            }
        });
    }

    private int convertDpToPixel(Context context, float dpValue) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                r.getDisplayMetrics()
        );
    }
}
