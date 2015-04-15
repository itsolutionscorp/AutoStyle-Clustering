class Complement

  def self.of_dna(str)
    str.tr("CGTA", "GCAU")
  end
  def self.of_rna(str)
    str.tr("GCAU", "CGTA")
  end
end
