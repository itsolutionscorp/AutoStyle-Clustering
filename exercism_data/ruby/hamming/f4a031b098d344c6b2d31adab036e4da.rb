class Hamming

  def self.compute(strand1, strand2)
    homologous_pairs(strand1, strand2).count { |pair| mutation?(pair) }
  end

  private

  def self.homologous_pairs(strand1, strand2)
    common_length = [strand1.length, strand2.length].min
    strand1.chars.zip(strand2.chars).take(common_length)
  end

  def self.mutation?(pair)
    pair.first != pair.last
  end

end
