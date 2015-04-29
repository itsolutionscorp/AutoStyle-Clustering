cfc = {
  "G" : "C",
  "C" : "G",
  "T" : "A",
  "A" : "U"
}

def to_rna(dna):
  return ''.join(map(char_for_char, dna)) 

def char_for_char(c):
  return cfc[c]
