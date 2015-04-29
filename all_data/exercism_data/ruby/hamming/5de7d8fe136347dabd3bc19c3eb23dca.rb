class Hamming
  class << self
    def compute(strand_1, strand_2)   
      count_mutations(paired_strands(strand_1, strand_2))
    end

    private

    def paired_strands(strand_1, strand_2)
      strand_2 = trim_strand_to_length(strand_2, strand_1)
      strand_2.chars.zip(strand_1.chars)
    end

    def trim_strand_to_length(long_strand, short_strand)
      if long_strand.length > short_strand.length
        long_strand.slice!(short_strand.length...long_strand.length)
      end
      long_strand
    end

    def count_mutations(comparison)
      comparison.count{|c1, c2| c1 != c2 }
    end

  end
end
