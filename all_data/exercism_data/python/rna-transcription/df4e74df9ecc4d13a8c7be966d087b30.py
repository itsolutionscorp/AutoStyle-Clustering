from string import maketrans

# always wanted to use str.translate()
rna_table = maketrans('GCTA', 'CGAU')

def to_rna(dna):
    return dna.translate(rna_table)
