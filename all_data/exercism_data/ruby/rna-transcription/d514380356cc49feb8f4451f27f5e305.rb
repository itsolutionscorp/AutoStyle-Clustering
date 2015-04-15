class Complement
  def self.of_dna(dna_strand)
    dna_strand.gsub!(/G/, 'c')
    dna_strand.gsub!(/C/, 'g')
    dna_strand.gsub!(/T/, 'a')
    dna_strand.gsub!(/A/, 'u')
    dna_strand.upcase

  end

  def self.of_rna(rna_strand)
    rna_strand.gsub!(/C/, 'g')
    rna_strand.gsub!(/G/, 'c')
    rna_strand.gsub!(/A/, 't')
    rna_strand.gsub!(/U/, 'a')
    rna_strand.upcase
  end
end
