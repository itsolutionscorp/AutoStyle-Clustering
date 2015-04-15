class DNA
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    @sequence.gsub('T','U')
  end
end
