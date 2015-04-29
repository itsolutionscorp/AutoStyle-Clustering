class Hamming
  def self.compute dna1, dna2
    distance = 0
    min_sequence = [dna1.length, dna2.length].min
    min_sequence.times.count { |c| dna1[c] != dna2[c] } 
  end
end
