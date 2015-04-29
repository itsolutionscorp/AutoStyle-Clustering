def to_rna(dna):
    dict = {'G':'C','C':'G','T':'A','A':'U'}
    return ''.join([dict[x] for x in dna])
