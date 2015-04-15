RNA_MAPPING = {
  'T': 'A',
  'A': 'U',
  'C': 'G',
  'G': 'C'
}

def to_rna(dna):
  result = ""
  for c in dna:
    result += RNA_MAPPING[c]
  return result
