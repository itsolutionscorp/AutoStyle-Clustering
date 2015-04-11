class Hamming
  def self.compute dna1, dna2
    counter = 0
    max_length = dna1.length < dna2.length ? dna1.length : dna2.length
    0.upto(max_length - 1) do |i|
      counter += 1 unless dna1[i] == dna2[i]
    end
    counter
  end
end
