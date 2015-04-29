def to_rna(dna):
  complements = { 'G': 'C',
                  'C': 'G',
                  'T': 'A',
                  'A': 'U' }
  rna = ""

  for n in dna:
    rna += complements[n]

  return rna
