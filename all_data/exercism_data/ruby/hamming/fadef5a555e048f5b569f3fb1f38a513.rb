class DNA

  def initialize(strand1)
    @strand1 = strand1
  end

  def hamming_distance(strand2)
    mutations(strand2).count
  end

private
  def mutations(strand2)
    comparison_index(strand2).find_all do |i|
      @strand1[i] != strand2[i]
    end
  end

  def comparison_index(strand2)
    (0..(min_strand_length(strand2) -1)).to_a
  end

  def min_strand_length(strand2)
    [@strand1.length, strand2.length].min
  end
end
