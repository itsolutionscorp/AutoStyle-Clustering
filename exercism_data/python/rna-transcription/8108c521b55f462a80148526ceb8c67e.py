def to_rna(seq):
  r = ""
  d = {'C' :'G', 'G':'C', 'A':'U', 'T': 'A'}
  for l in list(seq):
    r = r + d[l]
  return r
