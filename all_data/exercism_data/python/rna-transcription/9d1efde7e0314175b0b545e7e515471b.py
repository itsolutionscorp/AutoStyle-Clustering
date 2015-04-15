class DNA:
  def __init__(self,nucleotides):
    self.nucleotides = list(nucleotides)
    self.dna_to_rna_translations = self.generate_dna_to_rna_translations()

  def to_rna(self):
    rna_nucleotides = map(self.dna_to_rna,self.nucleotides)
    return ''.join(rna_nucleotides)

  def dna_to_rna(self,nucleotide):
    return self.dna_to_rna_translations[nucleotide]

  def generate_dna_to_rna_translations(self):
    return { 'A':'A', 'C':'C', 'G':'G', 'T':'U' }
