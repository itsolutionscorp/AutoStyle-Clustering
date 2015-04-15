class NucleicAcid < String

  module Abbreviations
    ADENINE = 'A'
    CYTOSINE = 'C'
    GUANINE = 'G'
    URACIL = 'U'
    THYMINE = 'T'
  end

end

class RibonucleicAcid < NucleicAcid

  # Extra method added for completeness.
  def to_dna
    DeoxyribonucleicAcid.new(gsub(Abbreviations::URACIL, Abbreviations::THYMINE))
  end

end

class DeoxyribonucleicAcid < NucleicAcid

  def to_rna
    RibonucleicAcid.new(gsub(Abbreviations::THYMINE, Abbreviations::URACIL))
  end

end
