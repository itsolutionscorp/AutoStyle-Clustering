class DNA
  attr_accessor :dna

  def initialize(dna)
    self.dna = dna
  end

  def to_rna
    dna.gsub('T', 'U')
  end
end
