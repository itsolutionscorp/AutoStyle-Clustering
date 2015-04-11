class Complement
  COMPLEMENTS = %w(GCAU CGTA)

  def self.of_dna(dna)
    dna.tr(*COMPLEMENTS)
  end

  def self.of_rna(rna)
    rna.tr(*COMPLEMENTS.reverse)
  end
end
