def to_rna(nucleotide):
    options = {'A':'U','T':'A','C':'G','G':'C'}
    rna_return = ''
    for letter in nucleotide:
        rna_return += options[letter]
    
    return rna_return
