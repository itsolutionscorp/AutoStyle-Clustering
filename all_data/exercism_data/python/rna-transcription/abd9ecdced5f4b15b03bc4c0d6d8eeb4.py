rna_map = { 'A' : 'U', 'G' : 'C', 'C' : 'G', 'T' : 'A' }
dna_map = {v: k for k,v in rna_map.items()}

def convert(map, strand):
   result, i = "", 0
   while i < len(strand):
       result += map[strand[i]]
       i += 1
   return result

def to_rna(dna):
   return convert(rna_map, dna)
 
def to_dna(rna):
   return convert(dna_map, rna)
