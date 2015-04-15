class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand.chars
  end

  def hamming_distance(other_strand)
    strand.zip(other_strand.chars).count do |mine, theirs|
      theirs && (mine != theirs)
    end
  end
end
