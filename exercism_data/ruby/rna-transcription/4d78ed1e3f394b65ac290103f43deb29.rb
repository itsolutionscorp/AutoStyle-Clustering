class Complement
  def self.of_dna(arg)
    arg.tr('CGTA', 'GCAU')
  end

  def self.of_rna(arg)
    arg.tr('CGUA', 'GCAT')
  end
end
