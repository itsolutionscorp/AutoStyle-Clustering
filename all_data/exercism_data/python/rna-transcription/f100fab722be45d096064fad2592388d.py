from string import maketrans

def to_rna(dna):
    trans = maketrans('GCTA', 'CGAU')
    return dna.translate(trans)
