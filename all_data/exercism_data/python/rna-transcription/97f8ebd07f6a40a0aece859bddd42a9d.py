DNA_RNA_MAP = dict(zip('GCTA','CGAU')) # so G->C, C->G, ...

def to_rna(strand):
  ret = []
  for c in strand:
    ret.append(DNA_RNA_MAP[c])
  return ''.join(ret)
