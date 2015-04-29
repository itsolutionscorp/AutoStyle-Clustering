# def to_rna(dna):
#   rna = ''
#   for ntide in dna:
#     if ntide == 'G':
#       rna += 'C'
#     elif ntide == 'C':
#       rna += 'G'
#     elif ntide == 'T':
#       rna += 'A'
#     elif ntide == 'A':
#       rna += 'U'
#     else:
#       print 'That ain\'t no nucleotide I ever heard of.'
#   return rna
# LOL LAZY
def to_rna(dna):
  tscript = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
  }
  rna = ''
  for ntide in dna:
    rna += tscript[ntide] 
  return rna
