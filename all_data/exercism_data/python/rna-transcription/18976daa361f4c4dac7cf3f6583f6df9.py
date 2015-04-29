class DNA:
  dna = ''
  
  def __init__(self, dna):
    self.dna = dna
    
  def to_rna(self):
    return self.dna.replace('T','U')
