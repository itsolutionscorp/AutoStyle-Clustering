from collections import Counter

class DNA(object):
  VALID_NUCLEOTIDES = "CGATU"

  def __init__(self, source):
    self._counts = {"C": 0, "G": 0, "A": 0, "T": 0}
    self._counts.update(Counter(source))

  def count(self, n):
    if not n in self.VALID_NUCLEOTIDES:
      raise ValueError("%s is not a nucleotide." % n)
    return self._counts[n] if n in self._counts else 0

  def nucleotide_counts(self):
    return self._counts
