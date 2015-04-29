class Hamming
  class << self
    def compute *strands
      nucleotide_pairs(*strands).count { |pair| mutation?(*pair) }
    end

  private

    def nucleotide_pairs *strands
      all_pairs(*strands).take_while(&:last)
    end

    def all_pairs first_strand, second_strand
      first_strand.chars.zip(second_strand.chars)
    end

    def mutation? first_nucleotide, second_nucleotide
      first_nucleotide != second_nucleotide
    end
  end
end
