def to_rna(strand):
    rna = ''
    for c in strand:
        if c=='A':
            rna += 'U'
        elif c=='G':
            rna += 'C'
        elif c=='T':
            rna += 'A'
        elif c=='C':
            rna += 'G'
        else: 
            # Your DNA is broken
            rna += c 

    return rna
