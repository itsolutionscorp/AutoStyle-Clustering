nucleotide_map = {
  'G': 'C',
  'C': 'G',
  'T': 'A',
  'A': 'U'
}

def to_rna(dna):
  c = ""
  for n in dna:
    c += nucleotide_map[n]
  return c
