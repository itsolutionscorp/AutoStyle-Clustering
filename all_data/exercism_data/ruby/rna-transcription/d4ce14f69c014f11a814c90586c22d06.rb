class DNA
  def initialize sequence
    @sequence = sequence
  end

  def to_rna
    sequence.transform_to_rna!
  end

  private
  attr_reader :sequence

  def transform_to_rna!
    sequence.gsub!('U', 'T').gsub!('T', 'U')
  end
end
