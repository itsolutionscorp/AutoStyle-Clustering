class Complement

  RNA_CHARS = 'CGAU'
  DNA_CHARS = 'GCTA'

  def self.of_dna(dna)
    dna.tr DNA_CHARS, RNA_CHARS
  end

  def self.of_rna(rna)
    rna.tr RNA_CHARS, DNA_CHARS
  end

end
