def to_rna(dna_strand):

  transcription_rules = {
    'C' : 'G',
    'G' : 'C',
    'A' : 'U',
    'T' : 'A',
  }

  return ''.join(transcription_rules[nucleotide] for nucleotide in dna_strand)
