#Converts individual nucleotides.
def dual(nucleotide):
    if nucleotide == 'G':
        return 'C'
    elif nucleotide == 'C':
        return 'G'
    elif nucleotide == 'T':
        return 'A'
    elif nucleotide == 'A':
        return 'U'
    else:
        return 'Error'

#Combines it all to convert a sequence.
def to_rna(code):
    code_list=list(code)

    for i in range(0,len(code_list)):
        code_list[i]= dual(code_list[i])

    return (''.join(code_list))
