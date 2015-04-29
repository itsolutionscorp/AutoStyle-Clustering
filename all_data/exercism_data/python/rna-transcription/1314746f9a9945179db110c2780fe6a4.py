from string import maketrans

dna = 'ACGT'
rna = 'UGCA'
trans = maketrans(dna, rna)

def to_rna(sequence):
    return ''.join((base.translate(trans) for base in sequence))
