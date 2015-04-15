complement = {
  'C': 'G',
  'G': 'C',
  'A': 'U',
  'T': 'A'
}

def to_rna(s):
  return ''.join([complement[i] for i in s])
