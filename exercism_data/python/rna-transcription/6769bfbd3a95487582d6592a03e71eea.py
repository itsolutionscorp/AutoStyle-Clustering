from string import maketrans

def to_rna(dna_str):
    return dna_str.translate(maketrans('GCTA', 'CGAU'))
