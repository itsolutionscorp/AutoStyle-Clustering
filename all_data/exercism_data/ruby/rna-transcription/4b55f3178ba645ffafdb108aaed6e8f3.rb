class Complement

  DNA_STRING = 'GCTA'
  RNA_STRING = 'CGAU'

  def self.of_dna(dna_str)
    dna_str.tr(DNA_STRING, RNA_STRING)
  end

  def self.of_rna(rna_str)
    rna_str.tr(RNA_STRING, DNA_STRING)
  end

end
