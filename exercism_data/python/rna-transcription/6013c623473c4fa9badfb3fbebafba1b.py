def to_rna(dna):
  translator = {'G':'C','C':'G','T':'A','A':'U'}

  DNA = ""
  for nuc in dna:
    DNA += translator[nuc]

  return DNA
