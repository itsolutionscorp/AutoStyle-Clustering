class Hamming

  def self.compute(dna1, dna2)
    new(dna1, dna2).compare
  end

  def initialize(dna1, dna2)
    @zipped = dna1.chars.zip(dna2.chars)
  end

  def compare
    @zipped.count do |pair|
      pair.first != pair.last
    end
  end

end
