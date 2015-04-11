def to_rna(dna):
    rna=''
    for c in dna:
        if c=='G':
            rna+='C'
        if c=='C':
            rna+='G'
        if c=='T':
            rna+='A'
        if c=='A':
            rna+='U'
    return rna
