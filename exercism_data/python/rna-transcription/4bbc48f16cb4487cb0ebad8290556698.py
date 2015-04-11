def to_rna(dnaStrand):
    rnaStrand = []
    for item in dnaStrand:
        if item == 'G': rnaStrand.append('C')
        if item == 'C': rnaStrand.append('G')
        if item == 'T': rnaStrand.append('A')
        if item == 'A': rnaStrand.append('U')
    return ''.join(rnaStrand)
