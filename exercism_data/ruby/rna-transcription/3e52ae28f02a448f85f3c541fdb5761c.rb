module Complement
  extend self

  DNA = "GCTA"
  RNA = "CGAU"

  def of_dna(dna)
    dna.tr DNA, RNA
  end

  def of_rna(rna)
    rna.tr RNA, DNA
  end
end
