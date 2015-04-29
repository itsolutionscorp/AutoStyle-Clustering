from string import maketrans

def to_rna(sequence):
    return sequence.translate(maketrans('GCTA','CGAU'))
