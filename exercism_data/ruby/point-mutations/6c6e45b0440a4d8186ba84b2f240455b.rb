class DNA

  def initialize strand
    @strand = strand
  end

  def hamming_distance matcher_strand
    if matcher_strand == @strand
      0
    else
      calculate_difference matcher_strand
    end
  end

private

  def calculate_difference matcher_strand
    @strand = @strand.slice! 0..matcher_strand.length - 1
    (@strand.chars.with_index.to_a - matcher_strand.chars.with_index.to_a).length
  end
end
