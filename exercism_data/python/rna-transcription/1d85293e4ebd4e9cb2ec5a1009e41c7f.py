def to_rna(dna_string):
    translation = { 'G': 'C',
                    'C': 'G',
                    'T': 'A',
                    'A': 'U',
                    }
    rna = []
    for base in dna_string:
        rna.append(translation[base])
    return ''.join(rna)
