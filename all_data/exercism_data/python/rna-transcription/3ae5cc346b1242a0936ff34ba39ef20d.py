class DNA(object):
  NUCLEOTIDES = {
    # I could add other nucleotides, though they would stay unused for now
    "thymidine": "T",
    "uracil": "U",
  }

  def __init__(self, sequence):
    self.sequence = sequence

  def to_rna(self):
    return self.sequence.replace(self.NUCLEOTIDES["thymidine"],
                                 self.NUCLEOTIDES["uracil"])
