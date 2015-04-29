def to_rna(dna):
    complement = {'G':'C','C':'G','T':'A','A':'U'}
    rna = ''
    for nucleotide in dna:
        rna += complement[nucleotide]
    return rna
