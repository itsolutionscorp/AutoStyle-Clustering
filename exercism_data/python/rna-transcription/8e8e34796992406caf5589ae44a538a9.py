dna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}


def to_rna(dna_str):

    rna = ''
    for x in dna_str:
        rna += dna.get(x)
    return rna
