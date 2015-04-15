from string import maketrans

def to_rna(rna):
  return rna.translate(maketrans("GCTA","CGAU"))
