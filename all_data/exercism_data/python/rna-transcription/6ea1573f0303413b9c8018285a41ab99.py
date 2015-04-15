from string import maketrans

def to_rna(code):
  return code.translate(maketrans("GCTA","CGAU"))
