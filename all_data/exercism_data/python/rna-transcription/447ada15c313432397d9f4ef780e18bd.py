def to_rna(dna_strand):
  dna_to_rna_map = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
  nucleotides = list(dna_strand)
  rna_strand = ''.join([dna_to_rna_map[_n] for _n in nucleotides])

  return rna_strand
