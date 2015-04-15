class DNA:
  def __init__(self, string):
    self.string = string

  def to_rna(self):
    return self.string.replace('T', 'U')
