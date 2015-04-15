conversion_dict = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
def to_rna(dna_string):
    rna_string = ''
    for nucleotide in dna_string:
        rna_string += conversion_dict[nucleotide]
    return rna_string
