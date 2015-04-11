def to_rna(input) :
  rna = {"G" : "C", "C" : "G", "T" : "A", "A" : "U"}
  result = ""
  for s in list(input):
    result = result+rna[s]

  return result
