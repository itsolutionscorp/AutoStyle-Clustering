from collections import Counter

class DNA(object):
  NUCLEOTIDES = {"dna": "CGAT",
                 "rna": "CGAU",
                 "all": "CGATU"}

  def __init__(self, source):
    self._counts = dict(zip(self.NUCLEOTIDES["dna"], [0, 0, 0, 0]))
    self._counts.update(Counter(source))

  def count(self, n, ntype="all"):
    if not n in self.NUCLEOTIDES[ntype]:
      raise ValueError("%s is not a nucleotide." % n)
    return self._counts[n] if n in self._counts else 0

  def nucleotide_counts(self):
    return self._counts
