class Hamming
  def self.compute(strand_1, strand_2)
    ary_1, ary_2 = strand_1.split(//), strand_2.split(//)
    length = [strand_1.length, strand_2.length].min
    ary_1.zip(ary_2).take(length).count { |a, b| a != b }
  end
end
