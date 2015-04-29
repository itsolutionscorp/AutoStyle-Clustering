class DNA(object):

  def __init__(self, dna_strand):
    self.dna_strand = dna_strand

  def to_rna(self):
    rna_strand = self.dna_strand.replace('T', 'U')
    return rna_strand
