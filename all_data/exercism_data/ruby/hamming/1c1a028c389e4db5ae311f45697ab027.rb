class Hamming
  class << self
    def compute(strand, other_strand)
      pointwise_comparisons(strand, other_strand).count { |c| c == :different }
    end

  private

    def match?(strand, other_strand, index)
      strand[index] == other_strand[index]
    end

    def pointwise_comparisons(strand, other_strand)
      range(strand).map do |index|
        match?(strand, other_strand, index) ? :same : :different
      end
    end

    def range(strand)
      0..strand.length
    end
  end
end
