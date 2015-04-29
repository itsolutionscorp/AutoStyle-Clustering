class DNA:
  """ RNA transcriber """

  def __init__(self,dna_string):
    self.dna_string = dna_string

  def to_rna(self):
    """ Transcribes the DNA string to RNA. """
    return self.dna_string.replace('T','U')
