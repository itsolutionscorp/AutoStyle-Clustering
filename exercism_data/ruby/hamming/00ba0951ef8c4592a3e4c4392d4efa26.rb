class DNA
  attr_reader :original_strand, :other_strand

  def initialize(original_strand)
    @original_strand = original_strand
  end

  def hamming_distance(other_strand)
    @other_strand = other_strand

    return 0 if lowest_common_length < 0

    0.upto(lowest_common_length - 1).inject(0) do |count, index|
      count += 1 if original_strand[index] != other_strand[index]
      count
    end
  end

  private

  def lowest_common_length
    [original_strand.length, other_strand.length].min
  end
end
