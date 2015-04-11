def to_rna(dna):
    rna_dict = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    
    # for every char in dna it with the key-value in our rna_dict translation
    # the resulting iterable is joined before assigned to 'rna' as a string
    rna = ''.join([rna_dict[c] for c in dna if c in rna_dict])

    return rna
