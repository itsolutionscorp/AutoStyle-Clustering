__author__ = 'shandr'

def to_rna(dna):
    rna_list = []
    dna_rna_map = {'G':'C','C':'G','T':'A','A':'U'}
    for letter in dna:
        rna_list.append(dna_rna_map[letter])
    rna = ''.join(rna_list)

    return rna
