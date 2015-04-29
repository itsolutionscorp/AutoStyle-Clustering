class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    groups_from(strand, other_strand).count { |group| different?(group) }
  end

  private

  def shorten(strand, length)
    strand.slice(0, length)
  end

  def different?(group)
    group.uniq.size > 1
  end

  def groups_from(strand, other_strand)
    shorten(strand, other_strand.size).chars.zip(other_strand.chars)
  end
end
