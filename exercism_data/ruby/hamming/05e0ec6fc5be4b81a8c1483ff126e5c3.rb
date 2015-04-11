class Hamming
  def self.compute(first_strand, second_strand)
    hamming = self.new(first_strand, second_strand)
    hamming.compare_strands
  end

  def compare_strands
    strand_range.inject(0) do |differences, index|
      unless strands_equal_at? index
        differences += 1
      end
      differences
    end
  end

  private

  def strands_equal_at?(index)
   @strand_1[index] == @strand_2[index]
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
