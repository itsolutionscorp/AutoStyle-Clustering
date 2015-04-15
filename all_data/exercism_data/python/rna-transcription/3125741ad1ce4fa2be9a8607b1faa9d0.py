def to_rna(dna):
    '''
    Converts DNA sequence to RNA sequence.
    Input string consists of letters G,C,T,and A.
    Output string consists of C,G,A, and U.
    '''
    complement = {'G':'C','C':'G','T':'A','A':'U'}
    return ''.join(complement[nucleotide] for nucleotide in dna)
