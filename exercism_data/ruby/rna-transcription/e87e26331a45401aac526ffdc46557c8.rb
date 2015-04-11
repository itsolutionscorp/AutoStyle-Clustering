class Complement
  DNA_N = 'GCTA'
  RNA_N = 'CGAU'

  def self.of_dna(dna)
    dna.tr(DNA_N,RNA_N)
  end

  def self.of_rna(rna)
    rna.tr(RNA_N,DNA_N)
  end
end
