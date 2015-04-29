class Complement
  def self.of_dna(nucleo)
    nucleo.tr("GCTA", "CGAU")
  end

  def self.of_rna(nucleo)
    nucleo.tr("CGAU", "GCTA")
  end
end
