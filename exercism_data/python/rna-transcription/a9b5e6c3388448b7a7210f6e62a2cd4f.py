dna_to_rna = str.maketrans({'T': 'A',
                            'A': 'U',
                            'G': 'C',
                            'C': 'G'})

def to_rna(dna):
    return dna.translate(dna_to_rna)
