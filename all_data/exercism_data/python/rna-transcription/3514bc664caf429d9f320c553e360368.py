def to_rna(dna):
    rna = ''
    for nucleotide in dna:
        if nucleotide == 'C':
            rna+='G'
        elif nucleotide == 'G':
            rna+='C'
        elif nucleotide == 'T':
            rna+='A'
        elif nucleotide == 'A':
            rna+='U'
    return rna
