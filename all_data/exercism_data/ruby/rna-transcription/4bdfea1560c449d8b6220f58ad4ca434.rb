class Complement
  def self.of_dna(original)
    original.tr "GCTA", "CGAU"
  end

  def self.of_rna(original)
    original.tr "CGAU", "GCTA"
  end
end
