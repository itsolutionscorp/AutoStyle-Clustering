
#fairly straight-forward, and due to python's inherent readability,
#probably not needing many comments.
#transcriptions dict makes for a readable, and referenceable in-code chart for quick access


transcriptions = {
        'G':'C',
        'C':'G',
        'T':'A',
        'A':'U'
}

def to_rna(DNAstrand):
    RNAstrand = ''##output string
    for nucleotide in DNAstrand:
        RNAstrand += transcriptions[nucleotide] #reading through DNA, append to RNA.
    return RNAstrand
