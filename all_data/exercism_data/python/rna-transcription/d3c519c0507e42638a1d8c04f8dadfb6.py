def to_rna(dna):
  dna_to_rna_mapping = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
  }

  return ''.join([dna_to_rna_mapping[nucleotide] for nucleotide in dna])
