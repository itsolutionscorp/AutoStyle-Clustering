def to_rna(dna):
    rna = ''
    garbage = ''
    for nucleotides in dna:
        if nucleotides is 'a' or nucleotides is 'A':
            rna += 'U'
        elif nucleotides is 't' or nucleotides is 'T':
            rna += 'A'
        elif nucleotides is 'c' or nucleotides is 'C':
            rna += 'G'
        elif nucleotides is 'g' or nucleotides is 'G':
            rna += 'C'
        elif nucleotides is ' ':
            rna += ' '
        else:
            garbage += nucleotides
    return rna
