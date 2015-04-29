class Hamming
  class << self
    def compute *strands
      nucleotide_pairs(*strands).count { |pair| mutation?(*pair) }
    end

  private

    def nucleotide_pairs first_strand, second_strand
      first_strand.chars.take(second_strand.length).zip(second_strand.chars)
    end

    def mutation? first_nucleotide, second_nucleotide
      first_nucleotide != second_nucleotide
    end
  end
end
