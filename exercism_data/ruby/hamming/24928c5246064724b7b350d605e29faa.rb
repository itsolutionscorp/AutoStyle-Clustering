class Hamming
  def self.compute(strand_a, strand_b)
    new(strand_a, strand_b).compute
  end

  def initialize(strand_a, strand_b)
    @strand_a = strand_a
    @strand_b = strand_b
  end

  def compute
    differing_duos.length
  end

  private

  def strand_a
    @strand_a.split('')
  end

  def strand_b
    @strand_b.split('')
  end

  def duos
    strand_a.zip(strand_b)
  end

  def differing_duos
    duos.select {|duo| duo[0] != duo[1] }
  end
end
