class Hamming
  def self.compute dna1, dna2
    counter = 0
    0.upto(dna1.length - 1) do |i|
      counter += 1 unless dna1[i] == dna2[i]
    end
    counter
  end
end
