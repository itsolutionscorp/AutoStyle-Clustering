class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(test_strand)
    (0...minimum_length(test_strand)).count{|index| test_strand[index] != @strand[index]}
  end

  def minimum_length(test_strand)
    [test_strand.length, @strand.length].min
  end
end
