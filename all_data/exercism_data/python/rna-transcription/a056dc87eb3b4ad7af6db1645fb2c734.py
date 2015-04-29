def to_rna(dna):
    return ''.join( map(map_n, [n for n in dna]) )

def map_n(n):
    if n == 'G':
        return 'C'
    elif n == 'C':
        return 'G'
    elif n == 'T':
        return 'A'
    elif n == 'A':
        return 'U'
    else:
        return ''
