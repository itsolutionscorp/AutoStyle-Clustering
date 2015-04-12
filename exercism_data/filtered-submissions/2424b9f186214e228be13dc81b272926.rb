# Calculates the Hamming difference between two DNA strands
class Hamming
  def compute(dna1, dna2)
    min_len = [dna1.length, dna2.length].min
    difference = 0
    (0..(min_len - 1)).each do |i|
      difference += 1 if dna1[i] != dna2[i]
    end
    difference
  end
end
