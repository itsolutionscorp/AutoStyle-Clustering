def to_rna(dna_strand):
  rna_transcript_map = {
    'G' : 'C',
    'C' : 'G',
    'T' : 'A',
    'A' : 'U'
  }
  transcriber = lambda nucleotide:rna_transcript_map[nucleotide]
  return ''.join(map(transcriber, dna_strand))
