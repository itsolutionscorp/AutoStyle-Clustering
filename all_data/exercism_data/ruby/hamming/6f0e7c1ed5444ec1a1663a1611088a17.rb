class Hamming
  def self.compute(strand1, strand2)
    dna1 = DNA.new(strand1)
    dna2 = DNA.new(strand2)
    pairs = dna1.combine_dna_to_compare(dna2)
    pairs.count { |pair| pair[1] && pair[0] != pair[1] }
  end
end

class DNA
  def initialize(strand)
    @strand = strand
  end

  def nucleotides
    @strand.chars
  end

  def combine_dna_to_compare(other_dna)
    nucleotides.zip(other_dna.nucleotides)
  end
end
