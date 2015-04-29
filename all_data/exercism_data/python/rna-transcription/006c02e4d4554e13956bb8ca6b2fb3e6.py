from string import maketrans

# always wanted to use str.translate()
_rna_table = maketrans('GCTA', 'CGAU')


def to_rna(dna):
    return dna.translate(_rna_table)
