def to_rna(dna):
    dna_rna_map = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }
    return ''.join(dna_rna_map[n] for n in dna)
