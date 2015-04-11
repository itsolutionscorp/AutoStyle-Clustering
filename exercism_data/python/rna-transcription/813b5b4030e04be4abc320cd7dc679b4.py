def to_rna(dna_string):
    mapping = {
        'G':'C',
        'C':'G',
        'T':'A',
        'A':'U',
    }
    return ''.join(mapping[k] for k in dna_string)
