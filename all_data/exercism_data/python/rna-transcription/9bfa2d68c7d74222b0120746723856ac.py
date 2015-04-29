TRANSLATIONS = {'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U'}

def to_rna(strand):
  rna = ''
  for nucleotide in strand:
    rna += TRANSLATIONS[nucleotide]
  return rna
