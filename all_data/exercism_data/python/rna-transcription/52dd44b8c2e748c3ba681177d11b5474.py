def to_rna(dna):
    comp = {'G': 'C',
            'C': 'G',
            'T': 'A',
            'A': 'U'}
    return ''.join([comp[nuc] for nuc in dna])
