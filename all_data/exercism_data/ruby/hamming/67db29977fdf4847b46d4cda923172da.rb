module Hamming

  def self.compute(dna1, dna2)
    if dna1.length != dna2.length
      raise ArgumentError.new('DNA strands must be equal lengths.')
    end

    distance(dna1.chars, dna2.chars)
  end

  def self.distance(dna1, dna2)
    difference(dna1, dna2).reduce(&:+)
  end

  def self.difference(dna1, dna2)
    dna1.zip(dna2).map { |nuc1, nuc2| (nuc1 <=> nuc2).abs }
  end

end
