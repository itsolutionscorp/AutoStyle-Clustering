class DNA
  THYMINE = 'T' unless defined? THYMINE
  URACIL  = 'U' unless defined? URACIL

  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    @sequence.gsub(THYMINE, URACIL)
  end
end
