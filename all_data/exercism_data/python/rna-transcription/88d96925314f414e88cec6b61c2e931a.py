def to_rna(str):
    s = ""
    for i in str:
        if i == 'G':
            s += 'C'
        elif i == 'C':
            s += 'G'
        elif i == 'T':
            s += 'A'
        elif i == 'A':
            s += 'U'
    return s    
