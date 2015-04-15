def to_rna(DNA):
 
#     DNA = DNA.replace('G','c')
#     DNA = DNA.replace('C','G')
#     DNA = DNA.replace('c','C')
#     DNA = DNA.replace('T','a')
#     DNA = DNA.replace('A','U')
#     DNA = DNA.replace('a','A')
    out = ''
    for i in list(DNA):
 
        if i == "G":
            out += 'C' 
            continue
        if i == "C":
            out += 'G' 
            continue 
        if i == "T":
            out += 'A' 
            continue
        if i == "A":
            out += 'U' 
            continue
  
    return out
