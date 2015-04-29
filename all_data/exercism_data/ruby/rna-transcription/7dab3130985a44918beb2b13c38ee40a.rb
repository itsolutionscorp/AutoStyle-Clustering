module Complement
  DNA = "GCTA"
  RNA = "CGAU"

  def self.of_dna(dna_sequence)
    dna_sequence.tr(DNA, RNA)
  end

  def self.of_rna(dna_sequence)
    dna_sequence.tr(RNA, DNA)
  end
end
