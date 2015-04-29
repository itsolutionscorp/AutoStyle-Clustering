def to_rna(dna_string):

    rna_dict = {'C':'G', 'G':'C', 'T':'A', 'A':'U'}
    
    return dna_string.translate(str.maketrans(rna_dict))
