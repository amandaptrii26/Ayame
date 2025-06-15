package com.example.latihan_praktikum8_amandaputri;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SettingFragmentTest {

    @Test
    public void calculateNumbers_shouldReturnCorrectSum() {
        SettingFragment settingFragment = new SettingFragment();

        int result = settingFragment.calculateNumbers(5, 10);

        assertEquals(15, result);
    }
}
