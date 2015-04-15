def to_rna(dna):
    '''Returns rna for a given dna strand'''
    return dna.translate(str.maketrans('GCTA', 'CGAU'))
