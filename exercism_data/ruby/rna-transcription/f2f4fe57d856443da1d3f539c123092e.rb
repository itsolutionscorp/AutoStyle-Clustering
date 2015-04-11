class RibonucleicAcid

  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def ==(other)
    self.to_str == other.to_str
  end

  def to_str
    sequence
  end

end

class DeoxyribonucleicAcid < RibonucleicAcid

  def to_rna
    rna_sequence = sequence.tr('T', 'U')
    RibonucleicAcid.new(rna_sequence)
  end

end
