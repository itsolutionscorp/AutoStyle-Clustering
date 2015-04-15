def to_rna(sequence):
    return sequence.translate(str.maketrans('TCGA', 'AGCU'))
