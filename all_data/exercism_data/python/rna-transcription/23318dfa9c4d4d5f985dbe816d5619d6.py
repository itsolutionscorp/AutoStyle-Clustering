import string

def to_rna(s):
  inputchars = "GCTA"
  outputchars = "CGAU"
  table = string.maketrans(inputchars, outputchars)
  return s.translate(table)
