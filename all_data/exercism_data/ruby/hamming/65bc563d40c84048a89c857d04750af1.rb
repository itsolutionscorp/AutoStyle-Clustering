class Hamming
  def self.compute(strandA, strandB)
    i, difference = 0, 0

    while i < shorter_strand_length(strandA, strandB)
      difference += 1 if strandA[i] != strandB[i]
      i += 1
    end

    difference
  end

  def self.shorter_strand_length(strandA, strandB)
    shorter = strandB
    shorter = strandA if strandA.length < strandB.length
    shorter.length
  end
end
