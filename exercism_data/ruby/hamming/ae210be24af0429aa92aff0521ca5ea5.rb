class Hamming
  class << self
    def compute(strand_1, strand_2)   
      trimmed = trim_strand_to_length(strand_2, strand_1)
      pairs = pair(strand_1, trimmed)
      count_mutations(pairs)
    end

    def count_mutations(comparison)
      comparison.count{|c1, c2| c1 != c2 }
    end

    def pair(strand_1, strand_2)
      strand_2.chars.zip(strand_1.chars)
    end

    def trim_strand_to_length(long_strand, short_strand)
      long_strand.slice!(short_strand.length...long_strand.length)
      long_strand
    end

  end
end
