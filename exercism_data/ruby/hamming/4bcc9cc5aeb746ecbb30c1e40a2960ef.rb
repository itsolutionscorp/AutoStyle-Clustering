class Hamming
  attr_reader :strand, :other_strand, :differences

  def initialize(strand, other_strand)
    @strand       = strand.chars
    @other_strand = other_strand.chars
    @differences  = 0
  end

  def self.compute(strand, other_strand)
    new(strand, other_strand).compare_strands
  end

  def compare_strands
    if equal_lengths?
      strand.each_with_index do |letter, index|
        @differences += 1 unless other_strand[index] == letter
      end
    end
    differences
  end

  def equal_lengths?
    strand.length == other_strand.length
  end

end
