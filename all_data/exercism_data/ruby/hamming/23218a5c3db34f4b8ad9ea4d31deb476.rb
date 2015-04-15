class DNA

  def initialize(strand1)
    @strand1 = strand1
  end

  def hamming_distance(strand2)
    mismatches = 0
    0.upto(min_strand_length(strand2) - 1) do |i|
      mismatches += 1 unless @strand1[i] == strand2[i]
    end
    mismatches
  end

  def min_strand_length(strand2)
    [@strand1.length, strand2.length].min
  end
end
