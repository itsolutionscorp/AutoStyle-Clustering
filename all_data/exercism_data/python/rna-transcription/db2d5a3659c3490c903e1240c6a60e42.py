def to_rna(dna):
    nucleotides = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }
    return "".join([nucleotides[i] for i in dna])
