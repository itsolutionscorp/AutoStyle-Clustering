class Hamming
  class << self
    def compute(strand_1, strand_2)
      pairs = pair(strand_1, strand_2)
      count_mutations(pairs)
    end

    def count_mutations(comparison)
      comparison.count{|c1, c2| c1 != c2 && c2 }
    end

    def pair(strand_1, strand_2)
      strand_2.chars.zip(strand_1.chars)
    end

  end
end
