module Complement
  DNA = "GCTA"
  RNA = "CGAU"

  def self.of_dna(a)
    a.tr(DNA, RNA)
  end

  def self.of_rna(a)
    a.tr(RNA, DNA)
  end
end
