package com.example.phasmophobiabook;

import androidx.annotation.NonNull;

import java.util.*;

public class GhostModel extends EvidenceModel {
    public String Name;
    public String Introduction;
    public String Strength;
    public String Weakness;

    @NonNull
    @Override
    public String toString() {
        List<String> evidence_list = new ArrayList<>();
        if (EMF_Level_5) {
            evidence_list.add("EMF 5级");
        }
        if (Spirit_Box) {
            evidence_list.add("通灵盒");
        }
        if (Freezing_Temperatures) {
            evidence_list.add("刺骨寒温");
        }
        if (Fingerprints) {
            evidence_list.add("指纹");
        }
        if (Ghost_Orb) {
            evidence_list.add("灵球");
        }
        if (Ghost_Writing) {
            evidence_list.add("鬼魂笔记");
        }
        StringBuilder evidence = new StringBuilder();
        for (int i = 0; i < evidence_list.size(); i++)
        {
            evidence.append(evidence_list.get(i));
            if (i < evidence_list.size() - 1)
            {
                evidence.append("、");
            }
        }
        return Name + "\n" + Introduction + "\n特征：" + Strength + "\n弱点：" + Weakness + "\n缺少证据：" + evidence.toString() + "。\n\n";
    }

    public String toNeededEvidenceString(boolean emf_level_5, boolean spirit_box, boolean freezing_temperatures, boolean fingerprints, boolean ghost_orb, boolean ghost_writing) {
        List<String> needed_evidence_list = new LinkedList<>();
        if (EMF_Level_5 && !emf_level_5) {
            needed_evidence_list.add("EMF 5级");
        }
        if (Spirit_Box && !spirit_box) {
            needed_evidence_list.add("通灵盒");
        }
        if (Freezing_Temperatures && !freezing_temperatures) {
            needed_evidence_list.add("刺骨寒温");
        }
        if (Fingerprints && !fingerprints) {
            needed_evidence_list.add("指纹");
        }
        if (Ghost_Orb && !ghost_orb) {
            needed_evidence_list.add("灵球");
        }
        if (Ghost_Writing && !ghost_writing) {
            needed_evidence_list.add("鬼魂笔记");
        }
        if (needed_evidence_list.isEmpty()) {
            needed_evidence_list.add("无");
        }
        StringBuilder needed_evidence = new StringBuilder();
        for (int i = 0; i < needed_evidence_list.size(); i++)
        {
            needed_evidence.append(needed_evidence_list.get(i));
            if (i < needed_evidence_list.size() - 1)
            {
                needed_evidence.append("、");
            }
        }
        return Name + "\n" + Introduction + "\n特征：" + Strength + "\n弱点：" + Weakness + "\n缺少证据：" + needed_evidence.toString() + "。\n\n";
    }
}
