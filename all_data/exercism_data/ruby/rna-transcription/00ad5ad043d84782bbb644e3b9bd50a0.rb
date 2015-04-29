class DNA
  def initialize(nucleotides)
    @nucleotides = nucleotides
  end
  
  attr_reader :nucleotides

  def to_rna
    nucleotides.gsub('T', 'U')
  end
end
