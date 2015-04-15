from operator import add


class InvalidNucleotide(ValueError):
  
  def __init__(self, nucleotide):
    super(InvalidNucleotide, self).__init__(
        '%s is not a nucleotide.' % nucleotide)


class DNA(object):
  nucleotides = "ACTGU"
  
  def __init__(self, strand):
    self.strand = list(strand)

  def count(self, nucleotide):
    if nucleotide not in self.nucleotides:
      raise InvalidNucleotide(nucleotide)

    self.nucleotide = nucleotide
    return reduce(add, map(self.match, self.strand), 0)

  def nucleotide_counts(self):
    return {
      'A': self.count('A'),
      'C': self.count('C'),
      'T': self.count('T'),
      'G': self.count('G')
    }

  def match(self, nucleotide):
    return self.nucleotide == nucleotide
