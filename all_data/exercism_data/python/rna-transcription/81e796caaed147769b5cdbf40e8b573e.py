nucleotides = str.maketrans('GCTA', 'CGAU')

def to_rna(dna):
    return dna.translate(nucleotides)
