class Complement

  DNA_KEYS = "GCTA"
  RNA_KEYS = "CGAU"

  def self.of_dna(string)
    string.tr(DNA_KEYS, RNA_KEYS)
  end

  def self.of_rna(string)
    string.tr(RNA_KEYS, DNA_KEYS)
  end

end
