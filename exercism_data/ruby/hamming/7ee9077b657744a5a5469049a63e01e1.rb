class Hamming

  def self.compute(strand1, strand2)
    new(strand1, strand2).distance
  end

  def initialize(strand1, strand2)
    @strand1, @strand2 = strand1, strand2
  end

  def distance
    homologous_pairs.count { |pair| mutation?(*pair) }
  end

  private

  attr_reader :strand1, :strand2

  def homologous_pairs
    shorter, longer = [strand1, strand2].sort_by(&:length)
    shorter.chars.zip(longer.chars)
  end

  def mutation?(n1, n2)
    n1 != n2
  end

end
