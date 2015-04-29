class Hamming
  def self.compute(strand_a, strand_b)
    new(strand_a, strand_b).compute
  end

  def initialize(strand_a, strand_b)
    @strand_a = strand_a
    @strand_b = strand_b
  end

  def compute
    differing_pairs.length
  end

  private

  def strand_a
    @strand_a.split('')
  end

  def strand_b
    @strand_b.split('')
  end

  def pairs
    strand_a.zip(strand_b)
  end

  def differing_pairs
    pairs.select {|pair| pair[0] != pair[1] }
  end
end
