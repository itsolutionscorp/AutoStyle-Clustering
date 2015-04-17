def to_rna(dna):

  rna = []
  for nucleotide in dna:
    if (nucleotide == 'G'):
      rna.append('C')
    elif (nucleotide == 'C'):
      rna.append('G')
    elif (nucleotide == 'T'):
      rna.append('A')
    elif (nucleotide == 'A'):
      rna.append('U')
    else:
      print "Nucleotide", nucleotide, "not known!"

  return "".join(rna)