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
    strands = @strand.chars.zip other_strand.chars
    strands.reject do |char, other_char|
      other_char ? (char == other_char) : true # If strand has extra chars we ignore them.
    end
  end
end
