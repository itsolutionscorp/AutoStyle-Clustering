class NucleicAcid < String

  module Abbreviations
    ADENINE = 'A'
    CYTOSINE = 'C'
    GUANINE = 'G'
    URACIL = 'U'
    THYMINE = 'T'
  end

  def initialize(code)
    super(code)
  end
end

class RibonucleicAcid < NucleicAcid

  # Extra method added for completeness.
  def to_dna
    DeoxyribonucleicAcid.new(self.gsub(Abbreviations::URACIL, Abbreviations::THYMINE))
  end

end

class DeoxyribonucleicAcid < NucleicAcid

  def to_rna
    RibonucleicAcid.new(self.gsub(Abbreviations::THYMINE, Abbreviations::URACIL))
  end

end
