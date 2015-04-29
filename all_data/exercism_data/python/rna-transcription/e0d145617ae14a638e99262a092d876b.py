mapping = {
    'C': 'G',
    'G': 'C',
    'T': 'A',
    'A': 'U'
}

def to_rna(dna):
    rna = ''
    for base in dna:
        rna += mapping[base]
    
    return rna
