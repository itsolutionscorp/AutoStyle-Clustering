class DNA

  attr_reader :strand
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(from_strand)
    count_mismatched_pairs(from_strand)
  end

  private

    def count_mismatched_pairs(other)
      paired = strand.codepoints.zip(other.codepoints)
      paired.count do |(first, second)|
        mismatch(first, second)
      end
    end

    def mismatch(n1, n2)
      n2 && n1 != n2
    end

end
