module Hamming
  def self.compute(strand_1, strand_2)
    [strand_1.length, strand_2.length].min.times.reject do |i|
      strand_1[i] == strand_2[i]
    end.length
  end
end
