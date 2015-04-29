from string import maketrans

conversions =  maketrans('GCTA', 'CGAU')
def to_rna(dna):
    return dna.translate(conversions)
