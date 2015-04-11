class Hamming
  def self.compute(strand_1, strand_2)
    combination = strand_1.split('').zip(strand_2.split(''))
    combination.count { |ary| ary[0] != ary[1] }
  end
end
