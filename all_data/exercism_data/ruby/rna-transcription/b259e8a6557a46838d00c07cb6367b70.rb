class Complement
  DNA = "GCTA"
  RNA = "CGAU"
  def self.of_dna(strands)
    strands.tr(DNA, RNA)
  end

  def self.of_rna(strands)
    strands.tr(RNA, DNA)
  end
end
