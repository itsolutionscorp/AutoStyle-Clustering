class DNA
  def initialize(strand)
    @orig_strand = strand
  end

  def hamming_distance(other_strand)
    strand = equalize_orig_strand_length(other_strand)
    count_differences(strand.chars, other_strand.chars)
  end

  private

  def count_differences(orig_strand, other_strand)
    orig_strand.zip(other_strand).count { |a, b| a != b }
  end

  def equalize_orig_strand_length(other_strand)
    @orig_strand[0...other_strand.length]
  end
end
