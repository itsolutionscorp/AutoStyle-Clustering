dict_map = {
    'A': 'U',
    'G': 'C',
    'T': 'A',
    'C': 'G'
}


def to_rna(dna):
    return ''.join([dict_map[x] for x in dna])
