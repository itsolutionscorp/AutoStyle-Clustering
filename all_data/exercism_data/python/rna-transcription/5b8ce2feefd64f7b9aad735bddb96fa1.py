
def to_rna (dna):
    rna = ''
    for x in dna:
        if x == 'A':
            rna += 'U'
        elif x == 'T':
            rna += 'A'
        elif x == 'C':
            rna += 'G'
        elif x == 'G':
            rna += 'C'
        else:
            return 0
        # the else is for the case that a character in the string
        # is not a valid nucleotide thus returns 0 for error 
    return rna
    # 
