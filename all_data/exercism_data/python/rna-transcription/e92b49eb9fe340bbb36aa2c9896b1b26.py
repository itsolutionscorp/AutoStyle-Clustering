def to_rna(arg):
    new = []

    for i in arg:
        if i == 'G':
            new.append('C')
        if i == 'C':
            new.append('G')
        if i == 'T':
            new.append('A')
        if i == 'A':
            new.append('U')

    seq = "".join(new)
    return seq
