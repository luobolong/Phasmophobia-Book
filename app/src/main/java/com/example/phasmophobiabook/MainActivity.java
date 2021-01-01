package com.example.phasmophobiabook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.MultiAutoCompleteTextView;

import java.util.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final List<GhostModel> ghosts = new ArrayList<GhostModel>() {{
        add(new GhostModel() {{
            Name = "魂魄"; Introduction = "魂魄是最常见的鬼魂，但它仍不容小觑。它通常会在死者死因不明的区域出现。"; Strength = "无。"; Weakness = "在魂魄附近点燃圣木会在一段时间内阻止它的攻击。"; Spirit_Box = true; Fingerprints = true; Ghost_Writing = true;
        }});
        add(new GhostModel() {{
            Name = "死灵"; Introduction = "死灵是最危险的鬼魂之一。它也是唯一已知的具有飞行能力的鬼魂，有时他还能穿墙。"; Strength = "死灵几乎不接触地面，所有他没有足迹。"; Weakness = "碰触到盐会在十秒钟内变得活跃。"; Spirit_Box = true; Fingerprints = true; Freezing_Temperatures = true;
        }});
        add(new GhostModel() {{
            Name = "幻影"; Introduction = "幻影是种能附身的鬼魂，通常可用通灵板召唤。它也会让周围的人感到恐惧。"; Strength = "看见幻影会大幅度降低你的理智。"; Weakness = "给他拍照会使他暂时消失。"; EMF_Level_5 = true; Ghost_Orb = true; Freezing_Temperatures = true;
        }});
        add(new GhostModel() {{
            Name = "骚灵"; Introduction = "鬼魂中最著名的一种鬼，也称为吵闹鬼。他可以通过操纵周围的物体来散播恐惧。"; Strength = "骚灵可以一次性投掷大量物体。"; Weakness = "骚灵在空无一物的房间内毫无用处。"; Spirit_Box = true; Fingerprints = true; Ghost_Orb = true;
        }});
        add(new GhostModel() {{
            Name = "女妖"; Introduction = "女妖是天生的猎人，它会攻击任何东西。它只会跟踪一个目标直至其死亡。"; Strength = "女妖一次只攻击一个人。"; Weakness = "女妖害怕十字架。"; EMF_Level_5 = true; Fingerprints = true; Freezing_Temperatures = true;
        }});
        add(new GhostModel() {{
            Name = "巨灵"; Introduction = "巨灵是一种地域意识很强的鬼魂，受到威胁时会发动攻击。它能够以相当快的速度移动。"; Strength = "如果目标在远处，巨灵会以更快的速度移动。"; Weakness = "关闭电源可防止巨灵使用其技能。"; Spirit_Box = true; Ghost_Orb = true; EMF_Level_5 = true;
        }});
        add(new GhostModel() {{
            Name = "梦魇"; Introduction = "梦魇是噩梦之源，他是黑暗中最强大的梦魇。"; Strength = "梦魇在黑暗中更有可能攻击你。"; Weakness = "开灯会降低他的攻击欲望。"; Spirit_Box = true; Ghost_Orb = true; Freezing_Temperatures = true;
        }});
        add(new GhostModel() {{
            Name = "亡魂"; Introduction = "亡魂移动缓慢，但很狂暴，他会肆意攻击。传言道他狩猎时会高速移动。"; Strength = "他狩猎时会高速移动。"; Weakness = "他找不到猎物时移动缓慢。"; EMF_Level_5 = true; Fingerprints = true; Ghost_Writing = true;
        }});
        add(new GhostModel() {{
            Name = "暗影"; Introduction = "暗影是个害羞鬼。证据表明，只要人够多他就不敢活动。"; Strength = "害羞意味着鬼魂更难被发现。"; Weakness = "如果附近有多个人，它不会进入狩猎模式。"; EMF_Level_5 = true; Ghost_Orb = true; Ghost_Writing = true;
        }});
        add(new GhostModel() {{
            Name = "恶魔"; Introduction = "恶魔会是你最不想遇到的鬼之一。众所周知，它会无缘无故地攻击你。"; Strength = "更具进攻性。"; Weakness = "使用通灵板向恶魔成功提问不会降低你的理智。"; Spirit_Box = true; Ghost_Writing = true; Freezing_Temperatures = true;
        }});
        add(new GhostModel() {{
            Name = "幽灵"; Introduction = "幽灵是回到现实世界的复仇之魂。"; Strength = "它会让你丧失更多的理智。"; Weakness = "在幽灵的房间里点燃圣木会使它避开这个房间很长时间。"; Ghost_Orb = true; Ghost_Writing = true; Freezing_Temperatures = true;
        }});
        add(new GhostModel() {{
            Name = "赤鬼"; Introduction = "赤鬼是恶魔的亲戚，拥有超强的力量。传言道他们会在猎物周围变得更加活跃。"; Strength = "当有人在附近时，他会更加活跃。赤鬼也可以高速移动物品。"; Weakness = "玩家更活跃会使赤鬼更容易被找到和识别。"; EMF_Level_5 = true; Spirit_Box = true; Ghost_Writing = true;
        }});
    }};
    private final List<GhostModel> matched_ghosts = new ArrayList<>();
    private final EvidenceModel evidence_not_found = new EvidenceModel();
    private final EvidenceModel evidence_found = new EvidenceModel();

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private MultiAutoCompleteTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonCopy = findViewById(R.id.buttonCopy);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        textView = findViewById(R.id.textView);

        buttonClear.setOnClickListener(this);
        buttonCopy.setOnClickListener(this);

        checkBox1.setOnClickListener(view -> FindGhosts());
        checkBox2.setOnClickListener(view -> FindGhosts());
        checkBox3.setOnClickListener(view -> FindGhosts());
        checkBox4.setOnClickListener(view -> FindGhosts());
        checkBox5.setOnClickListener(view -> FindGhosts());
        checkBox6.setOnClickListener(view -> FindGhosts());
        ShowAllGhosts();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonClear:
                onClickButtonClear(view);
                break;
            case R.id.buttonCopy:
                onClickButtonCopy(view);
                break;
            default:
                break;
        }
    }

    private void ShowAllGhosts() {
        StringBuilder sb = new StringBuilder();
        for (GhostModel ghost: ghosts) {
            sb.append(ghost.toString());
        }
        textView.setText(sb.toString().trim());
    }

    private void ShowGhosts(List<GhostModel> ghosts, boolean emf_level_5, boolean spirit_box, boolean freezing_temperatures, boolean fingerprints, boolean ghost_orb, boolean ghost_writing) {
        if (ghosts.isEmpty()) {
            textView.setText("无此种类型的鬼。");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (GhostModel ghost: ghosts) {
            sb.append(ghost.toNeededEvidenceString(emf_level_5, spirit_box, freezing_temperatures, fingerprints, ghost_orb, ghost_writing));
        }
        textView.setText(sb.toString().trim());
    }

    private void GetFoundEvidence() {
        evidence_found.EMF_Level_5 = checkBox1.isChecked();
        evidence_found.Spirit_Box = checkBox2.isChecked();
        evidence_found.Freezing_Temperatures = checkBox3.isChecked();
        evidence_found.Fingerprints = checkBox4.isChecked();
        evidence_found.Ghost_Orb = checkBox5.isChecked();
        evidence_found.Ghost_Writing = checkBox6.isChecked();
    }

    private void FindGhosts() {
        GetFoundEvidence();
        matched_ghosts.clear();
        for (GhostModel ghost: ghosts) {
            if ((!evidence_found.EMF_Level_5 || ghost.EMF_Level_5 == evidence_found.EMF_Level_5) &&
                    (!evidence_found.Spirit_Box || ghost.Spirit_Box == evidence_found.Spirit_Box) &&
                    (!evidence_found.Freezing_Temperatures || ghost.Freezing_Temperatures == evidence_found.Freezing_Temperatures) &&
                    (!evidence_found.Fingerprints || ghost.Fingerprints == evidence_found.Fingerprints) &&
                    (!evidence_found.Ghost_Orb || ghost.Ghost_Orb == evidence_found.Ghost_Orb) &&
                    (!evidence_found.Ghost_Writing || ghost.Ghost_Writing == evidence_found.Ghost_Writing)) {
                matched_ghosts.add(ghost);
            }
        }
        ShowGhosts(matched_ghosts, evidence_found.EMF_Level_5, evidence_found.Spirit_Box, evidence_found.Freezing_Temperatures, evidence_found.Fingerprints, evidence_found.Ghost_Orb, evidence_found.Ghost_Writing);
    }

    private void GetNotFoundEvidence() throws Exception {
        if (matched_ghosts.isEmpty()) {
            throw new Exception("没有匹配的鬼类型");
        }
        evidence_not_found.Reset();
        for (GhostModel ghost: matched_ghosts) {
            if (!evidence_found.EMF_Level_5 && ghost.EMF_Level_5) {
                evidence_not_found.EMF_Level_5 = true;
            }
            if (!evidence_found.Spirit_Box && ghost.Spirit_Box) {
                evidence_not_found.Spirit_Box = true;
            }
            if (!evidence_found.Freezing_Temperatures && ghost.Freezing_Temperatures) {
                evidence_not_found.Freezing_Temperatures = true;
            }
            if (!evidence_found.Fingerprints && ghost.Fingerprints) {
                evidence_not_found.Fingerprints = true;
            }
            if (!evidence_found.Ghost_Orb && ghost.Ghost_Orb) {
                evidence_not_found.Ghost_Orb = true;
            }
            if (!evidence_found.Ghost_Writing && ghost.Ghost_Writing) {
                evidence_not_found.Ghost_Writing = true;
            }
        }
        if (evidence_not_found.isAllFalse()) {
            throw new Exception("没有缺少的证据");
        }
    }

    private void CopyNotFoundEvidenceToClipBoard() {
        try {
            GetNotFoundEvidence();
        }
        catch (Exception ex){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示" ) ;
            builder.setMessage(ex.getMessage()) ;
            builder.setPositiveButton("确认" , null );
            builder.show();
            return;
        }
        String string_evidence_not_found = evidence_not_found.toTrueString();
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", string_evidence_not_found);
        cm.setPrimaryClip(mClipData);
    }

    private void onClickButtonClear(View view) {
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        checkBox5.setChecked(false);
        checkBox6.setChecked(false);
        ShowAllGhosts();
        matched_ghosts.clear();
    }

    private void onClickButtonCopy(View view) {
        CopyNotFoundEvidenceToClipBoard();
    }
}