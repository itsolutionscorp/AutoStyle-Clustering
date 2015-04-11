class DNA(object):
  def __init__(self, chain):
    self.chain = chain

  def to_rna(self):
    return self.chain.replace("T", "U")
