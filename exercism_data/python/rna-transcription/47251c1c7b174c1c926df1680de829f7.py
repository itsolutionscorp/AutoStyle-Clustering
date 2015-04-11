def to_rna(dna):
    rna = ''
    complements = { 'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U' }
    for c in dna:
        rna += complements[ c ]
    return rna
