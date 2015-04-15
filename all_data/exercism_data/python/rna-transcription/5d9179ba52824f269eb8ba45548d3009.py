from string import maketrans

def to_rna(dna):
    conversions =  maketrans('GCTA', 'CGAU')
    return dna.translate(conversions)
