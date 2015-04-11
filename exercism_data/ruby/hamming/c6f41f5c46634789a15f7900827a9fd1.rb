class Hamming

  def self.compute(dna1, dna2)
    hamming_count = 0
    min_length = min_length_of_dnas(dna1, dna2)
    for i in 0..min_length - 1
      if dna1[i] != dna2[i]
        hamming_count += 1
      end
    end
    return hamming_count
  end

  def self.min_length_of_dnas(dna1, dna2)
    return [dna1.length, dna2.length].min
  end
  private_class_method :min_length_of_dnas
end
