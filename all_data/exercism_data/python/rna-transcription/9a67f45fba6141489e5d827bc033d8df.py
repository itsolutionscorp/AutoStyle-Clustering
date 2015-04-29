def to_rna(dna):
    to_rna_dict = {
        'G' : 'C',
        'C' : 'G',
        'T' : 'A',
        'A' : 'U',
    }
    return ''.join([to_rna_dict[d] for d in dna])
