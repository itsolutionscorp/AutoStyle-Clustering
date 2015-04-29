class Hamming
  attr_reader :strand_a, :strand_b

  class << self
    def compute(strand_a, strand_b)
      new(strand_a, strand_b).difference
    end
  end

  def initialize(strand_a, strand_b)
    @strand_a, @strand_b = strand_a, strand_b
  end

  def difference
    @difference ||=
      aligned_strands.count { |a, b| a != b }
  end

  private

  def aligned_strands
    @aligned_strands ||=
      @strand_a.chars.zip(@strand_b.chars)
  end
end
