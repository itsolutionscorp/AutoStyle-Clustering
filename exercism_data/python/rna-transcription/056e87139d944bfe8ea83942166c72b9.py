rna_map = { 'A' : 'U', 'G' : 'C', 'C' : 'G', 'T' : 'A' }

def to_rna(strand):
   result = ""
   i = 0
   while i < len(strand):
       acid = strand[i]
       result += rna_map[acid]
       i += 1
   return result
