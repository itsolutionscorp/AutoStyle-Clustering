class Hamming
  def self.compute(dna1, dna2)
    distance = 0
    dna1.length.times do |i|
      if dna1[i] != dna2[i]
        distance += 1
      end
    end

    distance
  end
end
