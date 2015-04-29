
def to_rna(dna):
    strands = {"G": "C",
         "C": "G",
         "T": "A",
         "A": "U"}
    new = dna
    for i in dna:
        new = new.replace(i,strands[i])
    return new
            
