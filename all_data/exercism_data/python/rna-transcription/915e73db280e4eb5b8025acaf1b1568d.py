from string import maketrans

def to_rna(dna_sequence):
    return dna_sequence.translate(maketrans('GCTA', 'CGAU'))
