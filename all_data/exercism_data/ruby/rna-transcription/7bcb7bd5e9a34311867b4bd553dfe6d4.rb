class Complement
  class << self
    def of_dna(rna)
      rna.tr("GCTA", "CGAU")
    end

    def of_rna(dna)
      dna.tr("CGAU", "GCTA")
    end
  end
end
