
def to_rna (dna):
    rna = ''
    for x in range(len(dna)):
        if dna[x] == 'A':
            rna += 'U'
        elif dna[x] == 'T':
            rna += 'A'
        elif dna[x] == 'C':
            rna += 'G'
        elif dna[x] == 'G':
            rna += 'C'
        else:
            return 0
        # the else is for the case that a character in the string
        # is not a valid nucleotide thus returns 0 for error 
    return rna
    # 
