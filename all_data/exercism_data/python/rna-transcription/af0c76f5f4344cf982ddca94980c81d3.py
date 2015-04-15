__author__ = 'greg'

def to_rna(dna_string):
    """determines rna complement to dna string"""
    dna_to_rna = {'G':'C','C':'G','T':'A','A':'U'}
    rna = ""
    for x in range(len(dna_string)):
        rna += (dna_to_rna[dna_string[x]])
    return rna
