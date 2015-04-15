def to_rna(dna_strand):

  transcription_rules = {
    'C' : 'G',
    'G' : 'C',
    'A' : 'U',
    'T' : 'A',
  }

  rna_strand = ''

  for nucleotide in dna_strand:
    rna_strand += transcription_rules[nucleotide]

  return rna_strand
