class DNA
  attr_reader :sequence
  def initialize(dna_string)
    @sequence = String(dna_string).upcase
  end
  
  def to_rna
    sequence.tr('T', 'U')
  end
end
