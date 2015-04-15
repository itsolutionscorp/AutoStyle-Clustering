class Complement

  def self.of_dna(a)
    a.tr "GCTA", "CGAU"
  end

  def self.of_rna(a)
    a.tr "CGAU", "GCTA"
  end

end
