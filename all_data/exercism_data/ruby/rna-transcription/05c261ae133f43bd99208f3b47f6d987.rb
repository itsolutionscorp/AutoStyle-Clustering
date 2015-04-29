class DNA
  THYMINE = 'T'
  URACIL  = 'U'

  def initialize(dna)
    @dna = dna.to_s.upcase
    raise GeneticCodeError if invalid?
  end

  def to_rna
    @dna.tr(THYMINE, URACIL)
  end

  def invalid?
    @dna !~ /\A[ACGT]+[ACGT]*\Z/
  end
end

class GeneticCodeError < StandardError
  def message
    'Detected a genetically modified organism'
  end
end
