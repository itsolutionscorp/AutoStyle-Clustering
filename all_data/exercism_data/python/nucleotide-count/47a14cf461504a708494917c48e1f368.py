class DNA(object):
  class Nucleotides(object):
    DNA = set("GCAT")
    RNA = set("GCAU")
    ALL = DNA | RNA

  def __init__(self, source):
    self._source = source

  def count(self, n):
    if not n in self.Nucleotides.ALL:
      raise ValueError("%s is not a nucleotide." % n)
    return len([x for x in self._source if x == n])

  def nucleotide_counts(self):
    return {n: self.count(n) for n in self.Nucleotides.DNA}
