

def to_rna(strand):
  rna_convert = ""

  for char in strand:
  	if char == 'C':
  		rna_convert += 'G'
  	elif char == 'G':
  		rna_convert += 'C'
  	elif char == 'T':
  		rna_convert += 'A'
  	else:
  		rna_convert += 'U'

  return rna_convert
