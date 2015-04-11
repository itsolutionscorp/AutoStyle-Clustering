class RNATranscriptase
  THYMINE = "T"
  URACIL = "U"

  def self.to_rna(codons)
    codons.tr(THYMINE, URACIL)
  end
end

class DNA
  attr_reader :codons

  def initialize(codons)
    @codons = codons
  end

  def to_rna
    RNATranscriptase.to_rna(codons)
  end
end
