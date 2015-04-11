class DNA:
  def __init__(self, dna):
    self.dna = dna
    self.counts = { 'A': 0, 'C': 0, 'G': 0, 'T': 0, 'U': 0 }
    self._doCount()

  def _doCount(self):
    for c in self.dna:
      self.counts[c] += 1

  def count(self, nucleotide):
    if not nucleotide in self.counts:
      raise ValueError('%s is not a nucleotide.' % nucleotide)
    return self.counts[nucleotide]

  def nucleotide_counts(self):
    c = self.counts.copy()
    del c['U']
    return c
