class Hamming

  def self.compute(strand1, strand2)
    homologous_pairs(strand1, strand2).count { |pair| mutation?(pair) }
  end

  private

  def self.homologous_pairs(strand1, strand2)
    shorter, longer = [strand1, strand2].sort_by(&:length)
    shorter.chars.zip(longer.chars)
  end

  def self.mutation?(pair)
    pair.first != pair.last
  end

end
