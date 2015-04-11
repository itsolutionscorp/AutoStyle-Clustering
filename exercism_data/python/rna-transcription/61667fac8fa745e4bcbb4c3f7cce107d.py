def to_rna(dna):
    temp = ""
    for k in dna:
        if k == 'G':
            temp += 'C'
        elif k == 'C':
            temp += 'G'
        elif k == 'T':
            temp += 'A'
        elif k == 'A':
            temp += 'U'
    return temp
