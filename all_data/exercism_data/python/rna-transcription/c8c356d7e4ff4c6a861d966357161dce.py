def to_rna(dna):
    '''Replaces DNA with RNA sequence'''
    return dna.upper().replace('A', 'U').replace('T', 'A').replace('C', 'Q').replace('G', 'C').replace('Q', 'G')
