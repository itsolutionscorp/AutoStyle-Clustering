COMPLEMENT = {
        'A': 'U',
        'C': 'G',
        'G': 'C',
        'T': 'A',
        }

def to_rna(strand):
    return ''.join(COMPLEMENT[base] for base in strand)
