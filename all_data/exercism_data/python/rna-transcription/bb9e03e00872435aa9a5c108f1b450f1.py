def to_rna(dnaStrand):
    rnaStrand = []
    for item in dnaStrand:
        if item == 'G':
            rnaStrand.append('C')
        elif item == 'C':
            rnaStrand.append('G')
        elif item == 'T':
            rnaStrand.append('A')
        elif item == 'A':
            rnaStrand.append('U')
    return ''.join(rnaStrand)
