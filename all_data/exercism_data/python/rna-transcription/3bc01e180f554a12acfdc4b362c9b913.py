def to_rna(dna):

    new = list()
    #p

    for s in dna:
        if s == 'A':
            new.append('U')
        elif s == 'C':
            new.append('G')
        elif s == 'G':
            new.append('C')
        elif s == 'T':
            new.append('A')
    rna = ''.join(new)
    return rna
