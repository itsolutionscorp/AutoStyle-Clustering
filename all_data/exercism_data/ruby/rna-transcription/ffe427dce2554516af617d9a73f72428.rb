class Complement

  def self.of_dna(rna)
    rna.tr('GCTA', 'CGAU')
  end

  def self.of_rna(dna)
    dna.tr('CGAU', 'GCTA')
  end

end
