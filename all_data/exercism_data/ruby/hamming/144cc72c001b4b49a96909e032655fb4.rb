class DNA
  attr_reader :original_strand, :other_strand

  def initialize(original_strand)
    @original_strand = original_strand
  end

  def hamming_distance(other_strand)
    @other_strand = other_strand
    count = 0
    index = 0
    if lowest_common_length > 0
      lowest_common_length.times do
        if original_strand[index] != other_strand[index]
          count += 1
        end
        index += 1
      end
    end
    count
  end

  private

  def lowest_common_length
    [original_strand.chars.length, other_strand.chars.length].min
  end
end
