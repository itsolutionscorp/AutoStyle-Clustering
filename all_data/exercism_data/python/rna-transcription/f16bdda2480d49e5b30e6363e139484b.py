def to_rna(dna):
    rna = str()
    rna_dict = dict()
    rna_dict['G'] = 'C'
    rna_dict['C'] = 'G'
    rna_dict['T'] = 'A'
    rna_dict['A'] = 'U'
    for nucleotide in dna:
        rna += rna_dict[nucleotide]
    return rna
