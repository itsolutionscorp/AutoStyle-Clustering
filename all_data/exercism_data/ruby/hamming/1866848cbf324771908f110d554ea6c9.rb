class Hamming
  def self.compute(strand_0, strand_1)
    pairs(strand_0, strand_1).count{ |pair| StringPair.new(pair).mutation? }
  end

  def self.pairs(strand_0,strand_1)
    strand_0.chars.zip(strand_1.chars)
  end
end

class StringPair
  def initialize(pair)
    @char1, @char2 = pair[0], pair[1]
  end

  def mutation?
    valid_pair? && error?
  end

  def valid_pair?
    @char1 && @char2
  end

  def error?
    @char1 != @char2
  end
end
