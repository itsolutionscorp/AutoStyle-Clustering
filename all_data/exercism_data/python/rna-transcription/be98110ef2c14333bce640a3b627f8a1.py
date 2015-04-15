def to_rna(code):
    rna = []
    for letter in code:
        if   letter == 'G':
            rna.append('C')
        elif letter == 'C':
            rna.append('G')
        elif letter == 'T':
            rna.append('A')
        elif letter == 'A':
            rna.append('U')

    return ''.join(rna)
