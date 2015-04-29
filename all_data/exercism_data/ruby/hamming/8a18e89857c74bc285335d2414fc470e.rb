class Hamming
  def self.compute(strand_0, strand_1)
    zip(strand_0, strand_1).count{ |pair| mutation?(pair) }
  end

  private

  def self.zip(strand_0,strand_1)
    strand_0.chars.zip(strand_1.chars)
  end

  def self.mutation?(pair)
    valid_pair?(pair) && increment?(pair)
  end

  def self.valid_pair?(pair)
    pair[0] && pair[1]
  end

  def self.increment?(pair)
    pair[0] != pair[1]
  end
end
