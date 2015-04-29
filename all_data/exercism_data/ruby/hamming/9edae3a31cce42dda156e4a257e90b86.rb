class Hamming
  def self.compute(strand_a, strand_b)
    new(strand_a,strand_b).diff
  end

  def initialize(strand_a, strand_b)
    @strand_a, @strand_b = normalize_strands(strand_a, strand_b)
  end

  def diff
    (0...@strand_a.size).count {|i| @strand_a[i] != @strand_b[i] }
  end

  private

  def normalize_strands(a, b)
    a = a[0...b.size]
    b = b[0...a.size]
    [a, b]
  end
end
