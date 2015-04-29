def d2rNuclConv(nucl):
  if(nucl=='A'):
    return 'U'
  elif(nucl=='T'):
    return 'A'
  elif(nucl=='C'):
    return 'G'
  elif(nucl=='G'):
    return 'C'
  else:
    return ''
    
def to_rna(dna_strand):
  return ''.join(list(map(d2rNuclConv,dna_strand)))
