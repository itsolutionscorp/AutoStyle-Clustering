def to_rna(dna):
    rna = ''
    for nucleotide in dna:
        if nucleotide == 'G':
            rna += 'C'
        elif nucleotide == 'C':
            rna += 'G'
        elif nucleotide == 'T':
            rna += 'A'
        elif nucleotide == 'A':
            rna += 'U'
    return rna
