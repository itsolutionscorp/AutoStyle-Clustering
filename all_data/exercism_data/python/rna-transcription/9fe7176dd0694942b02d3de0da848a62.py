dna_to_rna_map = {'G': 'C',
                  'C': 'G',
                  'T': 'A',
                  'A': 'U'}

def to_rna(dna):
    return ''.join(dna_to_rna_map[n] for n in dna)
