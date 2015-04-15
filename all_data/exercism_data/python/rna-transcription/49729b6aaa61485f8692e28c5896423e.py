def rna(nucleotide):
  if nucleotide is 'G' : return 'C'
  if nucleotide is 'C' : return 'G'
  if nucleotide is 'T' : return 'A'
  if nucleotide is 'A' : return 'U'
  raise Exception('What are you feeding me?')

def to_rna(dna):
  return ''.join(map(rna, dna))
