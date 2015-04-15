cfc = {
  "G" : "C",
  "C" : "G",
  "T" : "A",
  "A" : "U"
}

def to_rna(dna):
  if not len(dna):
    return ""
  return char_for_char(dna[0]) + to_rna(dna[1:])

def char_for_char(c):
  return cfc[c]
