class Complement
  DNA_SEQ = "GCTA"
  RNA_SEQ = "CGAU"

  def self.of_dna(dna_string)
    dna_string.tr(DNA_SEQ, RNA_SEQ)
  end

  def self.of_rna(dna_string)
    dna_string.tr(RNA_SEQ, DNA_SEQ)
  end
end
