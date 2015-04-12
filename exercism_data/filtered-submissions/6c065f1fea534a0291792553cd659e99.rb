module Hamming
  def compute(dna1, dna2)
    diff = 0
    min_length = (dna1.size > dna2.size) ? dna2.size : dna1.size
    min_length.times do |i|
      diff += 1 if dna1[i] != dna2[i]
    end
    diff
  end
end
