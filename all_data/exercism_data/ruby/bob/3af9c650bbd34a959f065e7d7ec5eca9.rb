class DNA
  attr_reader :nucleotides

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_rna
    nucleotides.tr('T', 'U')
  end
end
