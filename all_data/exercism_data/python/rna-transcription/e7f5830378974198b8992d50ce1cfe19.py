def to_rna(rna):
    lst = []
    for r in rna:
        if r == 'C':
            lst.append('G')
        if r == 'G':
            lst.append('C')
        if r == 'T':
            lst.append('A')
        if r == 'A':
            lst.append('U')

    return ''.join(lst)
