class DNA:

  TO_RNA = {'G' :'C', 'C' :'G', 'T' :'A', 'A' :'U'}

  def __init__(self, strand):
    self.strand = strand

  def to_rna(self):
    RNA_strand = []
    for acid in self.strand:
      acid = DNA.TO_RNA[acid]
      RNA_strand.append(acid)
    return ''.join(RNA_strand)
