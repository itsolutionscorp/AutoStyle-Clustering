class Complement

  def self.of_dna(dna_string)
    dna_string.tr("GCTA", "CGAU")
  end

  def self.of_rna(rna_string)
    rna_string.tr("CGAU", "GCTA")
  end

end
