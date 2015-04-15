def to_rna(dna):
    """
    Apply the map_n function to each character in the dna string. 
    Join the resulting list into a string."""
    
    return ''.join( map(map_n, [n for n in dna]) )

def map_n(n):
    if n == 'G':
        return 'C'
    if n == 'C':
        return 'G'
    if n == 'T':
        return 'A'
    if n == 'A':
        return 'U'
    return ''
