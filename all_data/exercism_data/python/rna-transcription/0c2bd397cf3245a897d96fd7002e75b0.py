def to_rna(dna_strand):
  dna_to_rna_map = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
  rna_strand = ''.join([dna_to_rna_map[_n] for _n in dna_strand])

  return rna_strand
