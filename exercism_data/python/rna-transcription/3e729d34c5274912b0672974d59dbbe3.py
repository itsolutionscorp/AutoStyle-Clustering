def to_rna(dna):
    """
    Returns the RNA complement of a given DNA strand

    CONVERSIONS OF 'DNA' --> 'RNA':
        'G' --> 'C'
        'C' --> 'G'
        'T' --> 'A'
        'A' --> 'U'
    """
    
    rna = ''
    
    for nucleotide in dna:

        rna += {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}[nucleotide]

    return rna
