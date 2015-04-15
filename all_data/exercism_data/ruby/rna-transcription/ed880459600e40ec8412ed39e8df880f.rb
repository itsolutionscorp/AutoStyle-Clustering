class Complement
  DNA_SEQ = "GCTA"
  RNA_SEQ = "CGAU"

  def self.of_dna(dna)
    dna.tr(DNA_SEQ, RNA_SEQ)
  end

  def self.of_rna(rna)
    rna.tr(RNA_SEQ, DNA_SEQ)
  end
end
