nucleotides_transformation = {
  "G" : "C",
  "C" : "G",
  "T" : "A",
  "A" : "U"
}

def to_rna(dna):
  return "".join([convert(nucleotide) for nucleotide in dna])

def convert(nucleotide):
  return nucleotides_transformation.get(nucleotide)
