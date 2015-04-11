#dna.py
import re
def to_rna(dna):
    d_to_r={'G':'C','C':'G','T':'A','A':'U'}
    rna=list(dna)
    for i in range(len(rna)):
        rna[i]=d_to_r[list(dna)[i]] 
    return ''.join(rna)
