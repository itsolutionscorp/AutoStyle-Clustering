def to_rna(dna):
  rna = ''
  nucleotides = {'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U'}
  
  for nucl in dna:
    if nucl in nucleotides:
      rna += nucleotides[nucl]
    else:
      raise ValueError('Invalid nucleotide')
  
  return rna

    
