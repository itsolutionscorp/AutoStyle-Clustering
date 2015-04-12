module Hamming
  def compute(strand1, strand2)
    strand1.chars.each_with_index.count {|char, index| char != strand2[index]}
  end
end
