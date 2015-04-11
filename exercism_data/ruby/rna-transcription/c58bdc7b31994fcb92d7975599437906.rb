class DNA
  def initialize(sequence)
    @sequence = sequence.upcase
  end

  def to_rna
    @sequence.gsub('T', 'U')
  end
end
