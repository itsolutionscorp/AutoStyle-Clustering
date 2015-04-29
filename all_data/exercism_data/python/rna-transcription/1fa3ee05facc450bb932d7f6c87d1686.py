def to_rna(dna):
    transMap = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    rna = map(lambda n: transMap[n],dna)
    return ''.join(rna)
