class Hamming

  def self.compute(strandA, strandB)
    hamming_distance = 0
    length = find_shortest(strandA, strandB)

    0.upto(length - 1) do |index|
      hamming_distance += compare(strandA[index], strandB[index])
    end
    hamming_distance
  end


  def self.find_shortest(a, b)
    # Return length of shortest string
    a.length <= b.length ? a.length : b.length
  end

  def self.compare(a, b)
    # If compare returns -1 or 1, add 1 to the hamming distance
    result = (a <=> b)
    result == 0 ? result : result.abs
  end

end
