nucleotides = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
}

def to_rna(dna):
    rna = ''
    for n in dna:
        rna += nucleotides[n]
    return rna
