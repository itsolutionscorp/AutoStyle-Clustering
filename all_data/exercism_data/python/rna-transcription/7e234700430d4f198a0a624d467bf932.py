class DNA:
  def __init__(self, strand):
    self.dna_strand = strand
    self.dna_to_rna = {'G': 'G', 'C':'C', 'A': 'A', 'T': 'U'}

  def to_rna(self):
    rna_strand = ''
    for bp in self.dna_strand:
      try: 
        rna_strand += self.dna_to_rna[bp]
      except KeyError:
        print "Error: Invalid Polymer"
        return
    return rna_strand
