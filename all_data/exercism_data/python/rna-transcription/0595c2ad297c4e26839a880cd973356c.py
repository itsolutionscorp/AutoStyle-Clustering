MAP = {'G': 'C',
       'C': 'G',
       'T': 'A',
       'A': 'U'}

def to_rna(dna):
    return ''.join(MAP[base] for base in dna)
