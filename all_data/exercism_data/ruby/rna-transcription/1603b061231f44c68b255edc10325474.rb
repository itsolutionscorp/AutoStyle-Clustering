class DNA

  def initialize(sequence)
    @sequence = sequence
  end

  attr_reader :sequence

  def to_rna
    sequence.gsub(thymine, uracil)
  end

  def thymine
    "T"
  end

  def uracil
    "U"
  end

  private :thymine, :uracil

end
