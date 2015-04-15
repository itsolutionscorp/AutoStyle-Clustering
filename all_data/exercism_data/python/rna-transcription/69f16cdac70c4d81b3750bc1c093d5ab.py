def to_rna(dna_string):

    rna_dict = {'C':'G', 'G':'C', 'T':'A', 'A':'U'}
    
    return ''.join(rna_dict[nucleotide] for nucleotide in dna_string)
