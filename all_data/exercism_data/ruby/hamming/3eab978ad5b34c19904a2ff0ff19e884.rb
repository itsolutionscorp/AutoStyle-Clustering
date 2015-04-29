class Hamming
  def self.compute(strand_1, strand_2)
    combination = strand_1.split('').zip(strand_2.split(''))
    combination.select { |ary| ary[0] != ary[1] }.count
  end
end
