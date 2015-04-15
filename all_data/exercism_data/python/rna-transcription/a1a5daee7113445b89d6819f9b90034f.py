class Nucleotide(object):
  def __init__(self, base):
    self.base = base

  def __str__(self):
    return self.base

adenine = Nucleotide('A')
cytosine = Nucleotide('C')
guanine = Nucleotide('G')
thymine = Nucleotide('T')
uracil = Nucleotide('U')

nucleotides = [adenine, cytosine, guanine, thymine, uracil]
nucleotide_map = { nucleotide.base : nucleotide for nucleotide in nucleotides}

def strand_to_nucleotides(strand):
  return [nucleotide_map[base] for base in strand]

class DNA(object):
  map_to_rna = {
      adenine: adenine,
      cytosine: cytosine,
      guanine: guanine,
      thymine: uracil
      }

  def __init__(self, strand):
    self.nucleotides = strand_to_nucleotides(strand)

  def to_rna(self):
    rna_nucleotides = [DNA.map_to_rna[n] for n in self.nucleotides]
    return ''.join([str(n) for n in rna_nucleotides])
