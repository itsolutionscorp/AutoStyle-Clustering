class DNA
  attr_reader :dna

  def initialize(dna)
    @dna = dna
  end
  
  def to_rna
    replace_thymine_with_uracil
  end

  def replace_thymine_with_uracil
    dna.gsub('T','U')
  end
  
end
