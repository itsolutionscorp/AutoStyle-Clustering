#!/usr/bin/env python3
# -*- coding: utf-8 -*-


_translation = str.maketrans("GCTA", "CGAU")
def to_rna(dna):
    rna = dna.translate(_translation)
    return rna
