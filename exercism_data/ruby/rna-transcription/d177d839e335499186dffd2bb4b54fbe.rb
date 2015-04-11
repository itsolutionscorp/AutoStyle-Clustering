class Complement
  DNA_STR = 'GCTA'
  RNA_STR = 'CGAU'

  def self.of_dna(dna)
    dna.tr(DNA_STR, RNA_STR)
  end

  def self.of_rna(rna)
    rna.tr(RNA_STR, DNA_STR)
  end
end
