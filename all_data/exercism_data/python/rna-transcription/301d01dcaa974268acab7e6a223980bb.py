def to_rna(dna):
    dna_rna_map = {'A': 'U', 'C': 'G', 'G': 'C', 'T': 'A'}
    return reduce(lambda x, y: x + dna_rna_map[y], dna, '')
