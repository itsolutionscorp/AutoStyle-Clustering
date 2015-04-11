def to_rna(string):
    outstring = ''
    for c in string:
        if c == 'C':
            outstring += 'G'
        elif c =='G':
            outstring += 'C'
        elif c == 'T':
            outstring += 'A'
        elif c =='A':
            outstring += 'U'
    return outstring
