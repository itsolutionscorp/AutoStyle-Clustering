module Hamming
  def self.compute(dna1, dna2)
    diff = 0
    min_length = [dna1.size, dna2.size].min
    min_length.times do |i|
      diff += 1 if dna1[i] != dna2[i]
    end
    diff
  end
end
