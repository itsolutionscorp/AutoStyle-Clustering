def to_rna(dna):
    nda_rna_map = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    rna = ""
    for nucleotide in dna:
        rna = rna + nda_rna_map[nucleotide]

    return rna
