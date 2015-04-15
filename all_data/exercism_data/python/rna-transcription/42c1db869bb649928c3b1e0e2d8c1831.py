def to_rna(dna):
    translation = {
        'G': 'C', 
        'C': 'G',
        'T': 'A', 
        'A': 'U'
    }
    return ''.join(translation[ch] for ch in dna)
