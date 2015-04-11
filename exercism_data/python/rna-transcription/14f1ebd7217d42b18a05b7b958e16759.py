__author__ = 'shandr'

def to_rna(dna):
    rna  = ''
    dna_rna_map = {'G':'C','C':'G','T':'A','A':'U'}
    for letter in dna:
        rna = rna + dna_rna_map[letter]
    return rna
