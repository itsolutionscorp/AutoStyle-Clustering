def to_rna(dna):
    complement = {'G':'C','C':'G','T':'A','A':'U'}
    return ''.join(complement[nucleotide] for nucleotide in dna)
