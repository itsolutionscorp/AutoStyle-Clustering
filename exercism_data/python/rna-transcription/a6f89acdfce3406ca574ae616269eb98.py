def to_rna(nucleotide):
    rna = ""
    for letters in nucleotide:
        if letters == 'G':
            rna += 'C'
        elif letters == 'C':
            rna += 'G'
        elif letters == 'T':
            rna += 'A'
        elif letters == 'A':
            rna += 'U'
        
    return rna
