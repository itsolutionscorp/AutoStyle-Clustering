class Complement
  def self.of_dna(strand)
    strand.tr('CGTA', 'GCAU')
  end
  def self.of_rna(strand)
    strand.tr('CGUA', 'GCAT')
  end
end
