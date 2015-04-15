def to_rna(dna):
  if not len(dna):
    return ""
  return char_for_char(dna[0]) + to_rna(dna[1:])

def char_for_char(c):
  if c == "G":
    return "C"
  elif c == "C":
    return "G"
  elif c == "T":
    return "A"
  elif c == "A":
    return "U"
  else:
    return ""
