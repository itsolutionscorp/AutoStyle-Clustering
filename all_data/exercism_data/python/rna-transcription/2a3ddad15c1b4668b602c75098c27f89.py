
_dna_to_rna = {'C': 'G',
               'G': 'C',
               'A': 'U',
               'T': 'A'}

def to_rna(dna):
    return ''.join(_dna_to_rna[p] for p in dna)
    
