class AbstractMacromolecule

  def initialize(sequence)
    @sequence = sequence
  end

  def ==(other)
    self.to_str == other.to_str
  end

  def to_str
    sequence
  end

  private

  attr_reader :sequence
  
end

class RibonucleicAcid < AbstractMacromolecule

end

class DeoxyribonucleicAcid < AbstractMacromolecule

  def to_rna
    rna_sequence = sequence.tr('T', 'U')
    RibonucleicAcid.new(rna_sequence)
  end

end
