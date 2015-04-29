module Complement
  class << self
    DNA_CHARS = "GCTA"
    RNA_CHARS = "CGAU"

    def of_dna(dna_string)
      dna_string.tr(DNA_CHARS, RNA_CHARS)
    end

    def of_rna(rna_string)
      rna_string.tr(RNA_CHARS, DNA_CHARS)
    end
  end
end
