def to_rna(strand):
    rna = ''
    for c in strand:
        if c == 'G':
            rna = rna + 'C'
        elif c == 'C':
            rna = rna + 'G'
        elif c == 'T':
            rna = rna + 'A'
        elif c == 'A':
            rna = rna + 'U'
        else:
            return "Not valid DNA"

    return rna
