class NucleicAcid < String

  module Abbreviations
    ADENINE = 'A'
    CYTOSINE = 'C'
    GUANINE = 'G'
    URASIL = 'U'
    THYMINE = 'T'
  end

  def initialize(code)
    @code = code
    super
  end

  def to_s
    @code
  end

end

class RibonucleicAcid < NucleicAcid

  # Extra method added for completeness.
  def to_dna
    DeoxyribonucleicAcid.new(@code.gsub(Abbreviations::URASIL, Abbreviations::THYMINE))
  end

end

class DeoxyribonucleicAcid < NucleicAcid

  def to_rna
    RibonucleicAcid.new(@code.gsub(Abbreviations::THYMINE, Abbreviations::URASIL))
  end

end
