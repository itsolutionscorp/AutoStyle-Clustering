def to_rna(dna_sequence):
    dna = 'GCTA'
    rna = 'CGAU'
    trans_table = str.maketrans(dna, rna)
    return dna_sequence.translate(trans_table)
