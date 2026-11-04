/*
 * This file is part of packetevents - https://github.com/retrooper/packetevents
 * Copyright (C) 2022 retrooper and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package com.github.retrooper.packetevents.test;

import com.github.retrooper.packetevents.protocol.stream.NetStreamInput;
import com.github.retrooper.packetevents.protocol.world.chunk.palette.ListPalette;
import com.github.retrooper.packetevents.protocol.world.chunk.palette.MapPalette;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaletteLengthTest {

    private static NetStreamInput input(int size) {
        return new NetStreamInput(new ByteArrayInputStream(new byte[]{(byte) size}));
    }

    @Test
    public void rejectsOversizedListPaletteBeforeReadingEntries() {
        assertThrows(IllegalArgumentException.class, () -> new ListPalette(4, input(17)));
    }

    @Test
    public void rejectsOversizedMapPaletteBeforeReadingEntries() {
        assertThrows(IllegalArgumentException.class, () -> new MapPalette(5, input(33)));
    }

    @Test
    public void permitsEmptyPalettes() {
        assertEquals(0, new ListPalette(4, input(0)).size());
        assertEquals(0, new MapPalette(5, input(0)).size());
    }
}
