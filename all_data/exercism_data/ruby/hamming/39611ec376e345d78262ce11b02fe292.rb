# Calculates Hamming distance - an integer represented by the number of DNA mutations

class Hamming

  # Function that computes Hamming distance
  def self.compute(dna1, dna2)

    # Find shorter length and use that
    length = [dna1.length, dna2.length].min

    # Compare characters
    distance = 0
    length.times do |i|
      distance += 1 if dna1[i] != dna2[i]
    end

    return distance
  end

end
