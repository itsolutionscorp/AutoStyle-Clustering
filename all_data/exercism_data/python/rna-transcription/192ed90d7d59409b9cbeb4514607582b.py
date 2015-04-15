def to_rna(dna):
    translation = {'C':'G', 'G':'C', 'T':'A', 'A':'U'}
    return dna.translate(str.maketrans(translation))
