def to_rna(dna):
    rna = ''

    # Mapping from DNA to RNA complement
    complement = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

    # Build string of complements
    for nucleotide in dna:
        rna += complement[nucleotide]

    return rna
