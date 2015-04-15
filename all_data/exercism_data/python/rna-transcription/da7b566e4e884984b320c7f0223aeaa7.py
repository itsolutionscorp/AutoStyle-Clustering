# -*- coding: utf-8 -*-
#

def to_rna(dna):
    
    dna_to_rna = {'G': 'C',
                  'C': 'G',
                  'T': 'A',
                  'A': 'U'}

    rnalist = [dna_to_rna[nucleotid] for nucleotid in dna]
    rna = ''.join(rnalist)

    return rna
