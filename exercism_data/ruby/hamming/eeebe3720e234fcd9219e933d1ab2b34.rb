class DNA

  def initialize(strand1)
    @strand1 = strand1
  end

  def hamming_distance(strand2)
    mutations(strand2)
  end

private
  def mutations(strand2)
    comparison_indices(strand2).count do |i|
      @strand1[i] != strand2[i]
    end
  end

  def comparison_indices(strand2)
    (0..(min_strand_length(strand2) -1))
  end

  def min_strand_length(strand2)
    [@strand1.length, strand2.length].min
  end
end
