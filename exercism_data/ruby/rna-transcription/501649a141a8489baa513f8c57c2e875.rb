module Complement
  class << self
    def of_dna(dna)
      dna.tr("GCTA", "CGAU")
    end

    def of_rna(rna)
      rna.tr("CGAU", "GCTA")
    end
  end
end
