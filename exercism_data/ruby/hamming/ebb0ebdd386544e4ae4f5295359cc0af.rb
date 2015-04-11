# Calculates Hamming distance - an integer represented by the number of DNA mutations

class Hamming

  # Function that computes Hamming distance
  def self.compute(dna1, dna2)

    # Normalize dna strings
    dna1.upcase!
    dna2.upcase!

    # Make sure lengths are equal
    return 0 if dna1.length != dna2.length

    # Compare characters
    distance = 0
    0.upto(dna1.length) do |i|
      distance += 1 if dna1.slice(i, 1) != dna2.slice(i, 1)
    end

    return distance
  end

end
