def to_rna(input):
   rna = ''
   for c in input:
      if c is 'G':
         rna += 'C'
      elif c is 'C':
         rna += 'G'
      elif c is 'T':
         rna += 'A'
      elif c is 'A':
         rna += 'U'
      else:
         rna += 'what'

   return rna
