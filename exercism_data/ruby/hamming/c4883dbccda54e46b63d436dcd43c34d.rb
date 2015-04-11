class Hamming
  def self.compute(dna, other_dna)
    new(dna, other_dna).compute
  end

  def initialize(dna, other_dna)
    @dna = dna
    @other_dna = other_dna
  end

  def compute
    strands.count { |(a, b)| a != b }
  end

  private

  def strands
    dna.zip other_dna
  end

  def dna
    @dna.chars.take length
  end

  def other_dna
    @other_dna.chars.take length
  end

  def length
    [@dna, @other_dna].map(&:length).min
  end
end
