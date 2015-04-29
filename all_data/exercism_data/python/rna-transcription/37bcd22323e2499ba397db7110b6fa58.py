import string

class DNA(object):
  def __init__(self, strand):
    self.strand = strand.replace('T', 'U')

  def to_rna(self):
    return self.strand
