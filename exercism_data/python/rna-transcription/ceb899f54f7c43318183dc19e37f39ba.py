def to_rna(seq):
  return ''.join([{'A':'U', 'C':'G', 'G':'C', 'T':'A'}[a] for a in seq])
