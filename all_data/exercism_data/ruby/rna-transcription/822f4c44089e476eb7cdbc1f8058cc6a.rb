class DNA
  def initialize dna_string
    @dna_string = dna_string
  end

  def to_rna
    @dna_string.gsub(/[T]/, 'T' => 'U')
  end
end
