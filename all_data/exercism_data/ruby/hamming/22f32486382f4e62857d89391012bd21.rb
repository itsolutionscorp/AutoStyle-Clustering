class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(mutation)
    strand = @strand.chars.take(mutation.size)
    strand.zip(mutation.chars).count { |a,b| a != b }
  end
end
