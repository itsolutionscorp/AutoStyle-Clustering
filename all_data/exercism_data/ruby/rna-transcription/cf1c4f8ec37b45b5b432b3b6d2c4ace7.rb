class Complement
  def self.of_dna(dna_strand)
    fail ArgumentError if dna_strand.match(/[^GCTA]/)
    dna_strand.tr('GCTA', 'CGAU')
  end

  def self.of_rna(rna_strand)
    fail ArgumentError if rna_strand.match(/[^CGAU]/)
    rna_strand.tr('CGAU', 'GCTA')
  end
end
