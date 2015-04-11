module Hamming
  def self.compute(strand1, strand2)
    common_pairs(strand1, strand2).count {|a, b| mutation?(a, b)}
  end


  private

  def self.mutation?(a, b)
    a != b
  end

  def self.common_pairs(strand1, strand2)
    len = common_length(strand1, strand2)
    chars1 = strand1.chars.take len
    chars2 = strand2.chars.take len
    chars1.zip(chars2)
  end

  def self.common_length(strand1, strand2)
    [strand1.length, strand2.length].min
  end
end
