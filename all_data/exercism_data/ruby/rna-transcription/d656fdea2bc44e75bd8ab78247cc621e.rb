class RibonucleicAcid

  THYMINE  = ?T
  ADENINE  = ?A
  CYTOSINE = ?C
  GUANINE  = ?G

  def initialize(sequence)
    @sequence = sequence
  end

  def ==(other)
    self.to_str == other.to_str
  end

  def to_str
    sequence
  end

  protected

  attr_reader :sequence

end

class DeoxyribonucleicAcid < RibonucleicAcid

  URACIL = ?U

  def to_rna
    rna_sequence = sequence.tr(THYMINE, URACIL)
    RibonucleicAcid.new(rna_sequence)
  end

end
