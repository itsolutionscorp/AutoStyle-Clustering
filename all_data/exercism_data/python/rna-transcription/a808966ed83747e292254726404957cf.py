# Manozco


def to_rna(seq):
    dna_to_rna = {
        'G': "C",
        'C': "G",
        'T': "A",
        'A': 'U'
    }
    trans_table = str.maketrans(dna_to_rna)
    return seq.translate(trans_table)
