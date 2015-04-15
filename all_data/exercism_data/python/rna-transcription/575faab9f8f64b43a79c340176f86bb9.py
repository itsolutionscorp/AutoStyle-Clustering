DNA_RNA_MAP = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(s):
  ret = []
  for c in s:
    ret.append(DNA_RNA_MAP[c])
  return ''.join(ret)
