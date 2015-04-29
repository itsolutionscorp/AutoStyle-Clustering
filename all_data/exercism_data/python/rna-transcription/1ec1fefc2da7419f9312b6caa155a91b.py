from string import join

def to_rna(dna):
    mapping = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }
    return join(map(lambda c: mapping[c], dna), '')
