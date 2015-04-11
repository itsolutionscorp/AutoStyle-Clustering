class Complement
  class << self
    def of_dna dna_strand
      dna_strand.gsub!(/[GCTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
    end

    def of_rna rna_strand
      rna_strand.gsub!(/[GCUA]/, 'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T')
    end
  end
end
