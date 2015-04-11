def to_rna(dna):
    translation_table = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }
    return "".join(translation_table[c] for c in dna)
