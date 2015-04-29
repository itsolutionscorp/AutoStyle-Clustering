nucSwap = {'G':'C','C':'G','T':'A','A':'U'}

def to_rna(dnaSeq):
    splitSeq = list(dnaSeq)
    convSeq = ''
    for nucleotide in splitSeq:
        convSeq += nucSwap[nucleotide]
    return convSeq
