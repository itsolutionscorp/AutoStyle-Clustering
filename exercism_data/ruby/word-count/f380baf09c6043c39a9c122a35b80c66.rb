class DNA
  def initialize(sequence)
    @sequence = sequence.upcase
  end

  def to_rna
    @sequence.tr "T", "U"
  end
end
