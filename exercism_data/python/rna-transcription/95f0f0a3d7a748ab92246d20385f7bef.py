def to_rna(dna):
    mapping = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    return ''.join([mapping[n] for n in dna])