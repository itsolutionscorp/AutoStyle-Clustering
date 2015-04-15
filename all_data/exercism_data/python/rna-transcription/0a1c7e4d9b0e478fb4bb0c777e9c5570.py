def get_basepair(DNA):
  return {'C':'G',
          'G':'C',
          'A':'U',
          'T':'A'}[DNA]

def to_rna(dna):
    rna = ''
    for bp in dna:
        rna = rna + get_basepair(bp)
    return rna
