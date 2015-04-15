class DNA

  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    @sequence.gsub(thymidine, uracil)
  end

  private

  def thymidine
    'T'
  end

  def uracil
    'U'
  end
end
