class DNA
  def initialize(strand)
    @orig_strand = strand
  end

  def hamming_distance(other_strand)
    strand = equalize_orig_strand_length(other_strand)

    strand.chars.zip(other_strand.chars).reduce(0) do |count, pair|
      count += 1 if pair[0] != pair[1]
      count
    end
  end

  def equalize_orig_strand_length(other_strand)
    @orig_strand[0...other_strand.length]
  end
end
