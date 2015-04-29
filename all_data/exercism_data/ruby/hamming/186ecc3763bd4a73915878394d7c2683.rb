class DNA

  def initialize(strand1)
    @strand1 = strand1
  end

  def hamming_distance(strand2)
    mismatch = 0
    0.upto(shortest_strand(strand2) - 1) do |i|
      mismatch += 1 unless @strand1[i] == strand2[i]
    end
    mismatch
  end

  def shortest_strand(strand2)
    [@strand1.length, strand2.length].min
  end
end
