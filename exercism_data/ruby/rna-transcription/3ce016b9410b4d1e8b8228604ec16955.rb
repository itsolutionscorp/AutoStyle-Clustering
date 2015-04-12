class Complement

  def self.of_dna(rna)
    rna.tr('CGTA', 'GCAU')
  end

  def self.of_rna(dna)
    dna.tr('CGUA', 'GCAT')
  end

end