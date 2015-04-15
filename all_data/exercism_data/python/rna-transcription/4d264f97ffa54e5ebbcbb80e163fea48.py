from string import maketrans

rna_seq = maketrans('ACTG', 'UGAC')

def to_rna(dna_seq):
    return dna_seq.translate(rna_seq)
