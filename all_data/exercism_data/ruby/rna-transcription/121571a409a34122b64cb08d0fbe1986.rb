class DNA
  def initialize sequence
    @sequence = sequence
  end

  def to_rna
    transform_sequence!
  end

  private
  attr_reader :sequence

  def transform_sequence!
    sequence.gsub('U', 'T').gsub('T', 'U')
  end
end
