package com.example.phasmophobiabook;

import java.util.ArrayList;
import java.util.List;

public class EvidenceModel {
    public boolean EMF_Level_5 = false;
    public boolean Spirit_Box = false;
    public boolean Freezing_Temperatures = false;
    public boolean Fingerprints = false;
    public boolean Ghost_Orb = false;
    public boolean Ghost_Writing = false;

    public void Reset(){
        EMF_Level_5 = false;
        Spirit_Box = false;
        Freezing_Temperatures = false;
        Fingerprints = false;
        Ghost_Orb = false;
        Ghost_Writing = false;
    }

    public String toTrueString() {
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
        return evidence.toString();
    }

    public boolean isAllFalse() {
        return !EMF_Level_5 && !Spirit_Box && !Freezing_Temperatures && !Fingerprints && !Ghost_Orb && !Ghost_Writing;
    }
}
