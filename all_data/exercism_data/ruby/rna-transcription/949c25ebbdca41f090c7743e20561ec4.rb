class Complement

  def self.of_dna(rna)
    return rna.tr("GCTA", "CGAU")
  end

  def self.of_rna(dna)
    return dna.tr("CGAU", "GCTA")
  end

end
