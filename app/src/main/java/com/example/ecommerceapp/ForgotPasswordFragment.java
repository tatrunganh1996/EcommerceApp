package com.example.ecommerceapp;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment extends Fragment {


    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    private EditText email;
    private Button forgotPasswordBtn;
    private TextView goBack;
    private TextView stateText;

    private ViewGroup stateContainer;

    private ImageView emailIcon;

    private ProgressBar progressBar;

    private FrameLayout parentFrameLayout;

    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        email = view.findViewById(R.id.forgot_password_email);
        forgotPasswordBtn = view.findViewById(R.id.forgot_password_btn);
        goBack = view.findViewById(R.id.forgot_password_go_back);
        emailIcon = view.findViewById(R.id.forgot_password_email_icon);
        stateText = view.findViewById(R.id.forgot_password_state_text);

        progressBar = view.findViewById(R.id.forgot_password_progress_bar);
        stateContainer = view.findViewById(R.id.forgot_password_state_container);

        parentFrameLayout = getActivity().findViewById(R.id.sign_up_frame_layout);

        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        forgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(stateContainer);
                emailIcon.setVisibility(View.GONE);
                stateText.setVisibility(View.GONE);
//                progressBar.setVisibility(View.GONE);

//                TransitionManager.beginDelayedTransition(stateContainer);
//                emailIcon.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                forgotPasswordBtn.setEnabled(false);
                forgotPasswordBtn.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.sendPasswordResetEmail(email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    ScaleAnimation scaleAnimation = new ScaleAnimation(1,0,1,0,emailIcon.getWidth()/2,emailIcon.getHeight()/2);
                                    scaleAnimation.setDuration(100);
                                    scaleAnimation.setInterpolator(new AccelerateInterpolator());
                                    scaleAnimation.setRepeatMode(Animation.REVERSE);
                                    scaleAnimation.setRepeatCount(1);

                                    scaleAnimation.setAnimationListener(new Animation.AnimationListener(){
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            stateText.setText("Recovery email sent successfully ! check your inbox");
                                            stateText.setTextColor(getResources().getColor(R.color.successGreen));

                                            TransitionManager.beginDelayedTransition(stateContainer);
                                            emailIcon.setVisibility(View.VISIBLE);
                                            stateText.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {
                                            emailIcon.setImageResource(R.mipmap.email);

                                        }
                                    });

                                    emailIcon.startAnimation(scaleAnimation);
//                                    Toast.makeText(getActivity(), "Email is sent successfully", Toast.LENGTH_LONG).show();
                                }else {
                                    String error = task.getException().getMessage();
//                                    progressBar.setVisibility(View.GONE);
                                    forgotPasswordBtn.setEnabled(false);
                                    forgotPasswordBtn.setTextColor(Color.argb(50, 255,255,255));
                                    stateText.setText(error);
                                    stateText.setTextColor(getResources().getColor(R.color.colorPrimary));
                                    TransitionManager.beginDelayedTransition(stateContainer);
                                    stateText.setVisibility(View.VISIBLE);
                                    emailIcon.setVisibility(View.VISIBLE);
//                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                                progressBar.setVisibility(View.GONE);

                            }
                        });
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });
    }

    private void checkInput(){
        if (TextUtils.isEmpty(email.getText())){
            forgotPasswordBtn.setEnabled(false);
            forgotPasswordBtn.setTextColor(Color.argb(50,255,255,255));
        }else {
            forgotPasswordBtn.setEnabled(true);
            forgotPasswordBtn.setTextColor(Color.rgb(255,255,255));

        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_left, R.anim.slideout_right);
        transaction.replace(parentFrameLayout.getId(), fragment);
        transaction.commit();
    }
}
