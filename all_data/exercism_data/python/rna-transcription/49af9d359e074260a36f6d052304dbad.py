def to_rna(dna):
  bases = list(dna)
  bps = dict([('C', 'G'), 
             ('G', 'C'), 
             ('T', 'A'), 
             ('A', 'U')
             ])
  rna = [bps[b] for b in bases]
  return "".join(rna)
