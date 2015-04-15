class Complement
  def self.of_dna(dna_strand)
    fail ArgumentError if dna_strand.include?('U')
    dna_strand.tr('GCTA', 'CGAU')
  end

  def self.of_rna(rna_strand)
    fail ArgumentError if rna_strand.include?('T')
    rna_strand.tr('CGAU', 'GCTA')
  end
end
