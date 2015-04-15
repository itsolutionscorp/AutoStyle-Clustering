class Complement
  def self.of_dna(dna)
    rna = dna.tr('CGAT', 'GCUA')
  end
  def self.of_rna(rna)
    dna = rna.tr('CGUA', 'GCAT')
  end
end
