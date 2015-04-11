class DNA
  def initialize strand
    @strand = strand
  end

  def hamming_distance other_strand
    point_mutations(other_strand).count
  end

  def inspect
    @strand
  end

  private
  def point_mutations other_strand
    other_strand = other_strand.chars
    trimmed_strand = @strand.chars.take other_strand.size
    strands = trimmed_strand.zip other_strand
    strands.reject { |char, other_char| char == other_char }
  end
end
