from string import maketrans, translate

rna_conversion = maketrans('GCTA', 'CGAU')

def to_rna(dna):
    rna = dna.upper()
    rna = translate(dna, rna_conversion)
    return rna
