def to_rna(strand):
  rnaS = ''
  nucleotides = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

  for n in strand:
    rnaS += nucleotides[n]
    
  return rnaS
