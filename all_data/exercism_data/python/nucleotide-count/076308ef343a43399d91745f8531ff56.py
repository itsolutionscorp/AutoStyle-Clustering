from collections import Counter


class InvalidNucleotide(ValueError):

  def __init__(self, nucleotide):
    super(InvalidNucleotide, self).__init__(
        "{0} is not a nucleotide.".format(nucleotide))


class DNA(object):
  nucleotides = "ACTGU"

  def __init__(self, strand):
    self.strand = strand

  def count(self, nucleotide):
    if nucleotide not in self.nucleotides:
      raise InvalidNucleotide(nucleotide)

    return self.nucleotide_counts()[nucleotide]

  def nucleotide_counts(self):
    counter = Counter(A=0, C=0, T=0, G=0)
    counter.update(self.strand)
    return counter
