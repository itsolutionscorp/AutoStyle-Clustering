module Hamming
  def self.compute(one_strand, other_strand)
    one_strand.chars.zip(other_strand.chars).count { |one_char, other_char| one_char != other_char }
  end
end
