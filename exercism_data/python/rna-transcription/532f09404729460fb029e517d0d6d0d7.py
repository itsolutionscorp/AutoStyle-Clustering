def to_rna(seq):
    d2r = dict(A='U', T='A', G='C', C='G')
    return ''.join([v for x in seq for k, v in d2r.iteritems() if k == x])
