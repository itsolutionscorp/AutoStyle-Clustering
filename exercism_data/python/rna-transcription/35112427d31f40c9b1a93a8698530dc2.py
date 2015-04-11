def to_rna(dna):
   rna = ''
   for i in dna:
       if 'G' in i:
           rna += 'C'
       elif 'C' in i:
           rna += 'G'
       elif 'T' in i:
           rna += 'A'
       elif 'A' in i:
           rna += 'U'
   return rna
