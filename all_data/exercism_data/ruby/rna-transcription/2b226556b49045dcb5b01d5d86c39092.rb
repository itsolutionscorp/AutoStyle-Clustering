class Complement
  def self.of_dna(dna)
    dna.tr "GCTA", "CGAU"
  end

  def self.of_rna(rna)
    rna.tr "GCTUA", "CGAAT"
  end
end