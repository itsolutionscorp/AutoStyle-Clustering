def to_rna(dna_strand):
  rna_complement = ''
  transcriptions = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
  }

  for nucleotide in dna_strand:
    if transcriptions.has_key(nucleotide):
      rna_complement += transcriptions[nucleotide]
    else: 
      return None

  return rna_complement
