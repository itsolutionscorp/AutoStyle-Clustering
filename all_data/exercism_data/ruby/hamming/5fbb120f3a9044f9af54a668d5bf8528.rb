class Hamming

  # Computes the Hamming distance for two given DNA strands
  def self.compute(strA, strB) 
    # Treat the given strings as sequences of characters instead
    seqA = strA.split('')
    seqB = strB.split('')
    # Compute the hamming distance by comparing individual elements first
    #
    individual_distances = seqA.zip(seqB).map{ |e| (e[0] != e[1]) ? 1 : 0 }

    individual_distances.inject(0) { |sum, i| sum + i }
  end
end
