def to_rna(dna_string):

    rna_dict = {'C':'G', 'G':'C', 'T':'A', 'A':'U'}
    rna_string = ''
    
    for nucleotide in dna_string:
        rna_string += rna_dict[nucleotide]

    return rna_string
