#!/usr/bin/env python
# -*- coding: utf-8 -*-

compliments = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(dna):
    transcribed = ''

    for char in dna:
        transcribed += compliments[char]

    return transcribed
