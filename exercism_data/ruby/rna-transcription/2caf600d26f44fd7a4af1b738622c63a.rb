class Complement
  def self.of_dna(rna_strand)
    raise ArgumentError if rna_strand.include?('U')
    rna_strand.tr('GCTA', 'CGAU')
  end

  def self.of_rna(dna_strand)
    raise ArgumentError if dna_strand.include?('T')
    dna_strand.tr('CGAU', 'GCTA')
  end
end
