def to_rna(dna):
    '''Returns rna for a given dna strand'''
    return dna.replace('A', 'U') \
              .replace('T', 'A') \
              .replace('C', 'X') \
              .replace('G', 'C') \
              .replace('X', 'G') \
