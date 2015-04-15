class Hamming
  class << self
    def compute strand, other_strand
      difference_count pointwise_comparisons strand, other_strand
    end

  private

    def compare nucleotide, other_nucleotide
      nucleotide == other_nucleotide ? :same : :different
    end

    def compare_points corresponding_pairs
      corresponding_pairs.map { |nucleotides| compare *nucleotides }
    end

    def difference_count comparisons
      comparisons.count { |c| c == :different }
    end

    def pointwise_comparisons strand, other_strand
      compare_points strand.chars.zip other_strand.chars
    end
  end
end
