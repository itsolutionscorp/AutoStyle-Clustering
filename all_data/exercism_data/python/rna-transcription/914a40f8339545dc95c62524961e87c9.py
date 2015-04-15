def to_rna(dna):
  dna = dna.upper()
  rna = []
  solution = ""
  for i in range(len(dna)):
    if dna[i] == "A":
      rna.append("U")
    elif dna[i] == "T":
      rna.append("A")
    elif dna[i] == "C":
      rna.append("G")
    elif dna[i] == "G":
      rna.append("C")
  for foo in rna:
    solution = solution + foo
  return solution
