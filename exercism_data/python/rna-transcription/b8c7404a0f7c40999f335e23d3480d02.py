import string
rna_table = string.maketrans('GCTA', 'CGAU')
def to_rna(strand):
    return strand.translate(rna_table)
