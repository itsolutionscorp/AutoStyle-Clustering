import string

# always wanted to use str.translate()
rna_table = string.maketrans('GCTA', 'CGAU')

def to_rna(rna):
    return rna.translate(rna_table)
