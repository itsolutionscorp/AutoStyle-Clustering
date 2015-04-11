# -*- coding: utf-8 -*-

bps = {'G':'C','C':'G','T':'A','A':'U'}

def to_rna(sequence):
    return ''.join([bps[x] for x in sequence])
    

