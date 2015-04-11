def to_rna(dna):
  rna = ""
  dna_key = {
              "G":"C",
              "C":"G",
              "T":"A",
              "A":"U"
  }
  for nucl in dna:
    rna += dna_key[nucl]
  return rna
