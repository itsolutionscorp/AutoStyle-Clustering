class DNA:
  RNA_LOOKUP = { "C": "C", "G": "G", "A": "A", "T": "U"}

  def __init__(self, nucleotide_seq):
    self.__nucleotides = nucleotide_seq 
    
  def to_rna(self):
    return "".join(map(self.__dna_to_rna, self.__nucleotides))

  def __dna_to_rna(self, nucleotide):
    return self.RNA_LOOKUP[nucleotide]
