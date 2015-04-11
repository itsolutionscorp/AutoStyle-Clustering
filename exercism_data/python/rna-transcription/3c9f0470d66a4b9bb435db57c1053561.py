def to_rna(dna):
    '''Input dna (ACGT) and receive rna (ACGU)'''
    dna_to_rna = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    rna = ''
    for seq in dna:
        rna += dna_to_rna[seq]
    return rna
