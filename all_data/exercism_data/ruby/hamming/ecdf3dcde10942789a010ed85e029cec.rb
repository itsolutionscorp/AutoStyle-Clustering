class Hamming
  def self.compute(strand_1, strand_2)
    strand_1.length.times.count { |index| strand_1[index] != strand_2[index] }
  end
end
