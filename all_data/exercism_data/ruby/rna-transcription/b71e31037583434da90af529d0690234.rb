class Complement

  DNA_LETTERS = 'GCTA'
  RNA_LETTERS = 'CGAU'

  def self.of_dna(dna)
    dna.tr(DNA_LETTERS, RNA_LETTERS)
  end

  def self.of_rna(rna)
    rna.tr(RNA_LETTERS, DNA_LETTERS)
  end

end
