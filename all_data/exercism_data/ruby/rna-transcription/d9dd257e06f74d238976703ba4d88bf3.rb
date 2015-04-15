module Complement
  DNA = 'CATG'
  RNA = 'GUAC'

  def self.of_dna(seq)
    seq.tr(DNA, RNA)
  end

  def self.of_rna(seq)
    seq.tr(RNA, DNA)
  end
end
