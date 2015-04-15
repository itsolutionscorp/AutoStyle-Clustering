class Nucleotides(object):
  DNA = set("GCAT")
  RNA = set("GCAU")
  ALL = DNA | RNA

class DNA(object):
  def __init__(self, source):
    self._source = source

  def count(self, n):
    if not n in Nucleotides.ALL:
      raise ValueError("%s is not a nucleotide." % n)
    return self._source.count(n)

  def nucleotide_counts(self):
    return {n: self.count(n) for n in Nucleotides.DNA}
