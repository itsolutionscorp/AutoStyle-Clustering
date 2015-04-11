DNA_TO_RNA = { 
  'G' :'C',
  'C' : 'G',
  'T' : 'A',
  'A' : 'U',
}

def to_rna(dna):
  rna = ''
  for c in dna:
    if c not in DNA_TO_RNA:
      raise ValueError("illegal nucleotide '%s' in dna" % c)
    rna = rna + DNA_TO_RNA[c]
  return rna   
