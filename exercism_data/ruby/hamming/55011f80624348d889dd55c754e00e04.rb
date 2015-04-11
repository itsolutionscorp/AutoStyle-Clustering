class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(target)
    original, other = normalize_length(@strand, target)
    original.chars.zip(other.chars).count { |a, b| a != b }
  end

  private

  def normalize_length(a, b)
    length = [ a.length, b.length ].min
    [ a.slice(0, length), b.slice(0, length) ]
  end
end
