class Hamming
  def self.compute(dna1, dna2)
    distance, i = 0, 0
    length = [dna1.length, dna2.length].min

    while i < length
      if dna1[i] != dna2[i]
        distance += 1
      end

      i += 1
    end

    distance
  end
end