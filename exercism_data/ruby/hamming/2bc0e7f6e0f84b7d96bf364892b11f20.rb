class Hamming
  class << self
    def compute(strand_1, strand_2)
      nucleotide_comparison = strand_2.chars.zip(strand_1.chars)
      find_mutations(nucleotide_comparison)
    end

    def find_mutations(comparison)
      comparison.count{|c1, c2| c1 != c2 && c2 }
    end

  end
end
