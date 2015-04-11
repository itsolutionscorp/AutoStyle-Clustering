def to_rna(strand):
    complements = []
    for base in list(strand):
        if base == 'A':
            complements.append('U')
        elif base == 'C':
            complements.append('G')
        elif base == 'G':
            complements.append('C')
        elif base == 'T':
            complements.append('A')
    return ''.join(complements)
