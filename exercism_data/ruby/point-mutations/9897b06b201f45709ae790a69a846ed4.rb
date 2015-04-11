class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(test_strand)
    (0...minimum_length(test_strand)).count{|index| mutation?(test_strand[index], index)}
  end

  def minimum_length(test_strand)
    [test_strand.length, @strand.length].min
  end

  def mutation?(nucleotide, index)
    @strand[index] != nucleotide
  end
end
