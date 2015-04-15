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
    @strand.chars.reject.each_with_index do |char, index|
      other = other_strand[index] || char # Bit ugly.
      other == char
    end
  end
end
