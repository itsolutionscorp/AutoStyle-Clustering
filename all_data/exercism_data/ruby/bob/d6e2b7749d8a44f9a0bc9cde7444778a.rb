class DNA

  attr_accessor :dna_string, :rna_equivalent_string

  def initialize string
    @dna_string = string
  end

  def to_rna
    @rna_equivalent_string = dna_string.tr('T','U')
  end

end
