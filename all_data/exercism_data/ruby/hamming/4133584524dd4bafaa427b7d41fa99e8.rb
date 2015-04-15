class Hamming
  class << self
    def compute(strand, other_strand)
      pointwise_comparisons(strand, other_strand).count { |c| c == :different }
    end

  private

    def compare(nucleotide, other_nucleotide)
      nucleotide == other_nucleotide ? :same : :different
    end

    def pairs(strand, other_strand)
      sequence(strand).zip(sequence(other_strand))
    end

    def pointwise_comparisons(strand, other_strand)
      pairs(strand, other_strand).map { |pair| compare(*pair) }
    end

    def sequence(strand)
      strand.chars
    end
  end
end
