def to_rna(dna):
   rna = ''
   for i in list(dna):
       if 'G' in i:
           rna += 'C'
       if 'C' in i:
           rna += 'G'
       if 'T' in i:
           rna += 'A'
       if 'A' in i:
           rna += 'U'
   return rna
