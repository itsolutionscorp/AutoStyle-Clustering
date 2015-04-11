class DNA(object):
  dna_translation = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
  }

  def __init__(self, nucleotides):
    self.nucleotides = nucleotides

  def to_rna(self):
    rna = []

    for nucleotide in self.nucleotides:
      rna.append(self.dna_translation[nucleotide])

    return ''.join(map(str, rna));
