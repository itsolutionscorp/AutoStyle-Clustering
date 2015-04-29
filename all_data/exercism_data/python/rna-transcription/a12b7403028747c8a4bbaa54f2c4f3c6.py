class DNA(object):
  dic = {
    "thymidine": "T",
    "uracil": "U",
  }

  def __init__(self, state):
    self.state = state

  def to_rna(self):
    return self.state.replace(self.dic["thymidine"],
                                 self.dic["uracil"])
