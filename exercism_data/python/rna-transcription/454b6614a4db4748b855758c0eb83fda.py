def to_rna(string):
    rna = []
    for i in string:
        if i == 'A':
            rna.append('U')
        elif i == 'T':
            rna.append('A')
        elif i == 'C':
            rna.append('G')
        elif i == 'G':
            rna.append('C')
    return "".join(rna)
