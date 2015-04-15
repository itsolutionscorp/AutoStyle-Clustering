from string import maketrans

table = maketrans('GCTA', 'CGAU')

def to_rna(strand):
    return strand.translate(table)
