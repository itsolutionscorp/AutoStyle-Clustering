def to_rna(dna_string):
    dna_bases = list(dna_string)
    rna_bases = []
    for x in dna_bases:
        if x == 'A':
            rna_bases.append('U')
        elif x == 'T':
            rna_bases.append('A')
        elif x == 'G':
            rna_bases.append('C')
        elif x == 'C':
            rna_bases.append('G')
        else:
            pass
    rna_string = ''.join(rna_bases)
    return rna_string
