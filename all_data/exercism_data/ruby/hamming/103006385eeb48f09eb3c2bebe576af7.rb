class Hamming
  def self.compute(dna1, dna2)
    dna1.chars.zip(dna2.chars).inject(0) do |count, i|
      (i[0] != i[1]) ? count + 1 : count
    end
  end
end
