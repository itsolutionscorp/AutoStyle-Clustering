class DNA
  ADENINE  = 'A'
  CYTOSINE = 'C'
  GUANINE  = 'G'
  THYMINE  = 'T'
  URACIL   = 'U'

  def initialize(sequence)
    @sequence = sequence.upcase
  end

  def to_rna
    @sequence.gsub(THYMINE, URACIL)
  end
end
