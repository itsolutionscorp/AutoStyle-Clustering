def to_rna(strand):
    return ''.join([complement(ncl) for ncl in strand])

def complement(letter):
    replaces = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }

    return replaces[letter]
