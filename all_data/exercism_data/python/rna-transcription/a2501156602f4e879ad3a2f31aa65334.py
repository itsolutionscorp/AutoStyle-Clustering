def to_rna(dnaString):
    rnaString = ''
    for char in dnaString:
        if char == 'G':
            rnaString = rnaString + 'C'
        elif char == 'C':
            rnaString = rnaString + 'G'
        elif char == 'T':
            rnaString = rnaString + 'A'
        elif char == 'A':
            rnaString = rnaString + 'U'
    return rnaString
        

