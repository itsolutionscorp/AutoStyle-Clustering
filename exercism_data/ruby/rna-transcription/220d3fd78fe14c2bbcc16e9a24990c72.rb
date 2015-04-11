class Complement

  def self.of_dna(dna)

    dna.tr("CGTA","GCAU")

  end

  def self.of_rna(rna)

    rna.tr("GCAU","CGTA")

  end

end
