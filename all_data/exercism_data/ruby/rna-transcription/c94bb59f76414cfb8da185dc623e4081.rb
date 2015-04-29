module Complement

  DNA = 'ACGT'.freeze
  RNA = 'UGCA'.freeze

  def self.of_dna(rna)
    rna.tr(DNA, RNA)
  end

  def self.of_rna(dna)
    dna.tr(RNA, DNA)
  end
end
