class Complement

  def self.of_dna(comp)
    comp.tr('CGTA', 'GCAU')
  end

  def self.of_rna(comp)
    comp.tr('CGUA', 'GCAT')
  end
end
