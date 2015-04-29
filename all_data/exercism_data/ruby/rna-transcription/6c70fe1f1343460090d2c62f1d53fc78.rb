class DNA

  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    @sequence.tr('T', 'U')
  end
end
