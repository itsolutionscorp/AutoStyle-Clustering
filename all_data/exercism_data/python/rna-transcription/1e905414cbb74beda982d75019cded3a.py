def to_rna(seq):
    nts = []
    for nt in seq:
        if nt == 'G':
            nts.append('C')
        elif nt == 'C':
            nts.append('G')
        elif nt == 'A':
            nts.append('U')
        elif nt == 'T':
            nts.append('A')

    rna = ''.join(nts)
    return rna
