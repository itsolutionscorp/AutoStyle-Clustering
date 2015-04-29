from string import maketrans

dna = 'ACGT'
rna = 'UGCA'
trans = maketrans(dna, rna)

def to_rna(sequence):
    return sequence.translate(trans)
