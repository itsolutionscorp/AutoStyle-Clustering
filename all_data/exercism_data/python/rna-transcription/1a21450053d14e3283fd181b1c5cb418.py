dna_rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}


def to_rna(dna):
    rna = ""
    for strand in dna:
        rna += dna_rna[strand]
    return rna
