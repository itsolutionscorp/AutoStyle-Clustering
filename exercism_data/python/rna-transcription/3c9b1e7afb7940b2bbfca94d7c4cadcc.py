NUCLEOTIDES = {
    "G" : "C",
    "C" : "G",
    "T" : "A",
    "A" : "U"
  }

def to_rna(sequence):
  final = ""
  for x in sequence:
    final += NUCLEOTIDES[x]
  return final
