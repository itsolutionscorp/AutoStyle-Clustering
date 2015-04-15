# -*- coding: utf-8 -*-
class DNA:
    RNA_COMPLEMENT = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def to_rna(dna):
    rna = ""
    for base in dna:
        rna += DNA.RNA_COMPLEMENT[base]
    return rna
