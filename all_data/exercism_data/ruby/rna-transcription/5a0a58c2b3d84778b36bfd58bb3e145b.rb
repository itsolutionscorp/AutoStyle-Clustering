class DNA
  def initialize dna_string
    @dna_string = dna_string
  end

  def to_rna
    @dna_string.tr 'T', 'U'
  end
end
