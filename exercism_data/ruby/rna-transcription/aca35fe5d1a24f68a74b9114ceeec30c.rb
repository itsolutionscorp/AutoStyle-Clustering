class Complement
  def self.of_dna(strand)
    complementary_strand = []
    helicase = strand.split("")
    helicase.each do |nucleotide|
      if nucleotide == 'C'
        complementary_strand << 'G'
      elsif nucleotide == 'G'
        complementary_strand << 'C'
      elsif nucleotide == 'T'
        complementary_strand << 'A'
      elsif nucleotide == 'A'
        complementary_strand << 'U'
      end
    end
    complementary_strand.join
  end

  def self.of_rna(strand)
    complementary_strand = []
    helicase = strand.split("")
    helicase.each do |nucleotide|
      if nucleotide == 'C'
        complementary_strand << 'G'
      elsif nucleotide == 'G'
        complementary_strand << 'C'
      elsif nucleotide == 'U'
        complementary_strand << 'A'
      elsif nucleotide == 'A'
        complementary_strand << 'T'        
      end
    end
    complementary_strand.join("")
  end
end
