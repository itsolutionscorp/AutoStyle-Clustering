class DNA(object):

  def __init__(self, nucleotides):
    super(DNA, self).__init__()
    self.nucleotides = nucleotides

  def to_rna(self):
    return ''.join(map(lambda x: transcribe(x), self.nucleotides))
  
def transcribe(nucleotide):
  if nucleotide == "G":
    return "C"
  elif nucleotide == "C":
    return "G"
  elif nucleotide == "A":
    return "U"
  elif nucleotide == "T":
    return "A"
    
