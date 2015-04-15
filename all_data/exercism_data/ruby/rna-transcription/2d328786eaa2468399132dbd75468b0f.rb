class Complement

  def self.of_dna(x)
    x.tr('CGTA', 'GCAU')
  end

  def self.of_rna(x)
    x.tr('CGAU', 'GCTA')
  end

end
