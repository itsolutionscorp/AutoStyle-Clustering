def to_rna(inp):

    letters = list(inp)
    rna = list()
    for letter in letters:
        if letter == 'G':
            rna.append('C')
        elif letter == 'C':
            rna.append('G')
        elif letter == 'T':
            rna.append('A')
        elif letter == 'A':
            rna.append('U')
        else:
            continue
    string = ''.join(rna)
    return str(string)
