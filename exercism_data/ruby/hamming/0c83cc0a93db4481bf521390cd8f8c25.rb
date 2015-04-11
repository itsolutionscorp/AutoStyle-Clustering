class Hamming
  def self.compute(dna1, dna2)
    count = 0
    dna1.chars.each_with_index do |c, i|
      if i < dna2.length
        count += 1 if c != dna2[i]
      end
    end
    count
  end
end
