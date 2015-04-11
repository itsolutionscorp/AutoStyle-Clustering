import string

DNA_TO_RNA = string.maketrans('GCTA', 'CGAU')

def to_rna(sequence):
    return sequence.translate(DNA_TO_RNA)
