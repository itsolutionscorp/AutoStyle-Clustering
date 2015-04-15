def to_rna(dna):
    x = list(dna)
    
    def f(y):
        if y == 'G':
            return 'C'
        elif y == 'C':
            return 'G'
        elif y == 'T':
            return 'A'
        elif y == 'A':
            return 'U'
    return ''.join(map(f, x))
