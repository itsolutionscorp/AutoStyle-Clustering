import string

def to_rna(strand):
  t = string.maketrans("GCTA","CGAU")
  return string.translate(strand,t)
