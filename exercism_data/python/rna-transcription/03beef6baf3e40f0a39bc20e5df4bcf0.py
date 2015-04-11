# Requires Python 3x

def to_rna(dna):
    dna_grammar = "GCTA"
    rna_grammar = "CGAU"
    trans = str.maketrans(dna_grammar, rna_grammar)
    return dna.translate(trans)
