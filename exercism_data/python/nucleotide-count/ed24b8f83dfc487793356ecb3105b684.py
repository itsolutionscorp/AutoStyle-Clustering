class DNA(object):

  def __init__(self, nucleotides):
    self.nucleotides = nucleotides

  def count(self, nucleotide):
    if "GACTU".count(nucleotide) == 0:
      raise ValueError('{0} is not a nucleotide.'.format(nucleotide))
    return self.nucleotides.count(nucleotide)

  def nucleotide_counts(self):
    return dict(map(lambda n: (n, self.count(n)), "ACTG"))
    
