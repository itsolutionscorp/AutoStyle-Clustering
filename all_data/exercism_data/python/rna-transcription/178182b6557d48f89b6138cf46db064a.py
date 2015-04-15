from string import maketrans

DNA_TO_RNA = maketrans('ACGT', 'UGCA')

def to_rna(sequence):
    return sequence.translate(DNA_TO_RNA)
