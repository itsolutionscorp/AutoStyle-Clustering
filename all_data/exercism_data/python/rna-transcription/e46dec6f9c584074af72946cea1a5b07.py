def to_rna(seq):
    dna_to_rna = dict(A='U', T='A', G='C', C='G')
    return ''.join(
        [v for x in seq for k, v in dna_to_rna.iteritems() if k == x])
