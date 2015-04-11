class DNA
  attr_reader :dna

  def initialize(dna)
    @dna = dna
  end

  def to_rna
    dna.gsub('T', 'U')
  end
end
