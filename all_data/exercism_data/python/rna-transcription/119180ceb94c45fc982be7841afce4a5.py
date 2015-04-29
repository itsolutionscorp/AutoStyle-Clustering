def to_rna(strand):
    complement = {'G':'C', 'C':'G','T':'A','A':'U'}
    return ''.join([complement[x] for x in strand])
