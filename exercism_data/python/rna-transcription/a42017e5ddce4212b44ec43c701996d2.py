from string import replace

class DNA:
  def __init__(self, string):
    self.string = string
  def to_rna(self):
    return replace(self.string, "T", "U")
