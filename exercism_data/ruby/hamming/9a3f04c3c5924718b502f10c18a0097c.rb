module Hamming

  extend self

  def compute(strand_1, strand_2)
    @hamming_distance = 0
    @strand_1 = strand_1.split(//)
    @strand_2 = strand_2.split(//)
    determine_smaller_strand
    compare_strands
    @hamming_distance
  end

  def determine_smaller_strand
    if @strand_1.length <= @strand_2.length
      @smaller_strand = @strand_1
    else
      @smaller_strand = @strand_2
    end
  end

  def compare_strands
    i = 0
    @smaller_strand.length.times do
      if @strand_1.at(i) != @strand_2.at(i)
        @hamming_distance += 1
      end

      i += 1
    end
  end
end
