class Hamming
  class << self
    def compute *strands
      nucleotide_pairs(*strands).count { |pair| mutation?(*pair) }
    end

  private

    def nucleotide_pairs strand1, strand2
      strand1.chars.take(strand2.length).zip(strand2.chars)
    end

    def mutation? nucleotide1, nucleotide2
      nucleotide1 != nucleotide2
    end
  end
end
