def to_rna(dna):
    rnamap = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    rna = ''
    for nucleotide in dna:
        rna += rnamap[nucleotide]
    return rna
