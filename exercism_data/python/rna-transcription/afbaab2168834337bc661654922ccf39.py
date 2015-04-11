def to_rna(dna):
    dna_rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    rna = ''
    for n in dna:
        rna += dna_rna[n]
    return rna
