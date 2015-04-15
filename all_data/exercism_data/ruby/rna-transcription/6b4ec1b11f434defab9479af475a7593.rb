class Complement

  DNA = "GCTA"
  RNA = "CGAU"

  def self.of_dna(dna)
    dna.tr(DNA, RNA)
  end

  def self.of_rna(rna)
    rna.tr(RNA, DNA)
  end

end
