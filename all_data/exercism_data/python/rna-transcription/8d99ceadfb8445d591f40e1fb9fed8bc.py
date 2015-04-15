class DNA:
  """
  A strand of DNA
  """
  def __init__(self,strand):
    """
    Constructor sets the strand
    """
    self.strand=strand

  def to_rna(self):
    """
    Returns the strand transcribed into RNA
    """
    lookup={'G':'C','C':'G','T':'A','A':'U'}
    rna=""
    for n in self.strand:
      rna+=lookup[n]
    return rna
