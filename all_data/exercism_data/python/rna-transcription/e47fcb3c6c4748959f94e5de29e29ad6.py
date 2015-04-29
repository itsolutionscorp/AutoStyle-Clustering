def to_rna(dna):

   lookup = { 
               'G': 'C',
               'C': 'G',
               'T': 'A',
               'A': 'U',
            }

   rna = ""
      
   for nucleotide in dna:
      complement = lookup[nucleotide]
      rna = "%s%s" % (rna, complement)

   return rna
