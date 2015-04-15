from string import maketrans

def to_rna(nucleotide):
    trans_table = maketrans('GCTA', 'CGAU')
    return nucleotide.translate(trans_table)
