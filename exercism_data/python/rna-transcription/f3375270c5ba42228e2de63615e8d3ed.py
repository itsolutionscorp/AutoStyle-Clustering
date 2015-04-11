def to_rna(dna):
   rna = ''
   rna_trans = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
   for i in dna:
       rna += rna_trans[i]
   return rna
