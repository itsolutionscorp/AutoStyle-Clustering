#!/usr/bin/env python

def to_rna(dna):
    dna = dna.replace('C','1').replace('G','2').replace('A','U').replace('T','A')
    dna = dna.replace('1','G').replace('2','C')
    return dna
