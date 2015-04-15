class Hamming
  def self.compute(strand_1, strand_2)
    combination = strand_1.chars.zip(strand_2.chars)
    combination.count { |ary| ary[0] != ary[1] }
  end
end
