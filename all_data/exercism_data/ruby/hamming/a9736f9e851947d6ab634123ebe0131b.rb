class Hamming
  def self.compute(first_strand, second_strand)
    hamming = self.new(first_strand, second_strand)
    hamming.compare_strands
  end

  def compare_strands
    strand_range.count do |index|
     strands_different_at? index
    end
  end

  private

  def strands_different_at?(index)
   @strand_1[index] != @strand_2[index]
  end

  def initialize(first_strand, second_strand)
    @strand_1 = first_strand
    @strand_2 = second_strand
  end

  def strand_length
    [@strand_1.length, @strand_2.length].min
  end

  def strand_range
    0...strand_length
  end
end
