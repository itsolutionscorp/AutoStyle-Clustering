class Complement
  DNA_SEQ = 'CGTA'
  RNA_SEQ = 'GCAU'

  def self.of_dna(arg)
    arg.tr(DNA_SEQ, RNA_SEQ)
  end

  def self.of_rna(arg)
    arg.tr(RNA_SEQ, DNA_SEQ)
  end
end
