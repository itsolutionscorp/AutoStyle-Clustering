def to_rna(dna):
    table = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    rna = [table[nucleotides] for nucleotides in dna]
    return ''.join(rna)
