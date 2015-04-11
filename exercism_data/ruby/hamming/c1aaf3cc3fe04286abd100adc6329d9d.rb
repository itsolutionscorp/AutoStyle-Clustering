module Hamming
  def self.compute(strand_1, strand_2)
    0.upto(strand_1.length).count { |i| strand_1[i] != strand_2[i] }
  end
end
