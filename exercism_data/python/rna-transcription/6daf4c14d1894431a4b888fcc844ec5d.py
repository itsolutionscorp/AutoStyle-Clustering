rna_table = {'A':'U','G':'C','T':'A','C':'G'}

def to_rna(dna):
    rna = ''
    for nucleotide in dna:
        rna += rna_table[nucleotide]
    return rna
