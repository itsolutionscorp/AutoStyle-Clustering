from string import maketrans

def to_rna(sequence):
    complements = maketrans('GCTA', 'CGAU')
    return sequence.translate(complements)
