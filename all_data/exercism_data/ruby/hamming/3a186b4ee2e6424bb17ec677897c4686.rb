class Hamming
  def self.compute(dna1, dna2)
    count = 0
    dna1.chars.each_index do |index|
      count += 1 unless dna1[index] == dna2[index]
    end
    count
  end
end
