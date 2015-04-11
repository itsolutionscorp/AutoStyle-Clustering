module Hamming
  def self.compute(strand_1, strand_2)
    strand_1.each_char.with_index.count { |c, i| c != strand_2[i] }
  end
end
