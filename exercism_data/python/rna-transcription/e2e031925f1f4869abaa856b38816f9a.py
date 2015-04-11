# -*- coding: utf-8 -*-
from string import maketrans

def to_rna(dna):
    table = maketrans('GCTA','CGAU')
    return dna.translate(table)



