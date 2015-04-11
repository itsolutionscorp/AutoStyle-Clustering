from string import maketrans
def to_rna(input) :
  dna = "GCTA"
  rna = "CGAU"
  transtab = maketrans(dna, rna)
  return input.translate(transtab)
