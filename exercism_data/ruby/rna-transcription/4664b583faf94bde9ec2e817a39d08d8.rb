class Complement

  def self.of_dna(dna_strand)
    dna_strand.tr!('CGTA', 'GCAU')
  end

  def self.of_rna(rna_strand)
    rna_strand.tr!('GCAU', 'CGTA')
  end
end
