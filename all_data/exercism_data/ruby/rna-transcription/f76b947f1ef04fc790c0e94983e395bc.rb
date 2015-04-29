class Complement
  def self.of_dna(nucleotides)
    nucleotides.tr('GCAT', 'CGUA')
  end

  def self.of_rna(nucleotides)
    nucleotides.tr('GCAU', 'CGTA')
  end
end
