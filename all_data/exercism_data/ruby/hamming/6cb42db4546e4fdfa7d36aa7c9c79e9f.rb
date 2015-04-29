class Hamming

  def self.compute(strand1, strand2)
    distance = 0

    strandA = strand_to_array(strand1)
    strandB = strand_to_array(strand2)

    size = use_shorter_strand(strandA, strandB)

    strandA.take(size).each_with_index { |char, index|
      distance += 1 if char != strandB[index]
    }

    distance
  end

  def self.use_shorter_strand(strand1, strand2)
    [strand1.size, strand2.size].min
  end

  def self.strand_to_array(strand)
    strand.split('')
  end

end
