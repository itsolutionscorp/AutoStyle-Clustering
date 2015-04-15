class DNA:
  
  def __init__(self, dna):
    self.dna = dna

  def count(self, element):
      if ['A', 'C', 'G', 'T', 'U'].count(element) == 1:
        return self.dna.count(element)
      else:
        raise ValueError("%s is not a nucleotide." % element)

  def nucleotide_counts(self):
    result = {}
    for nu in ['A', 'C', 'G', 'T']:
      result[nu] = self.count(nu)
    return result
