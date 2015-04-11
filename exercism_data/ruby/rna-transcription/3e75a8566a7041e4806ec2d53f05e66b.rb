class Complement
  def self.of_dna(dna)
    dna.tr('ACTG', 'UGAC')
  end

  def self.of_rna(rna)
    rna.tr('UGAC', 'ACTG')
  end
end
