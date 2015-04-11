#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
Given a DNA strand, returns its RNA complement (per RNA transcription).
"""

import string


def to_rna(dna):
    """
    Given a DNA strand, its transcribed RNA strand is formed by replacing
    each nucleotide with its complement:

    * `G` -> `C`
    * `C` -> `G`
    * `T` -> `A`
    * `A` -> `U`
    """

    table = string.maketrans("GCTA", "CGAU")
    return dna.translate(table)
