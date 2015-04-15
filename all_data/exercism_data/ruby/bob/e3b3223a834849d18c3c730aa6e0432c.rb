class DNA
  attr_reader :dna_type

  def initialize(dna_type)
    @dna_type = dna_type
  end

  # Translates a DNA string to a RNA string.
  def to_rna
    @dna = @dna_type.tr('T', 'U')
  end

end
