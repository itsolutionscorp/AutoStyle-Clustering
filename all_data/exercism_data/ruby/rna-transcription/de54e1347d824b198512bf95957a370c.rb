DNA = Struct.new(:dna) do
  def to_rna
    dna.tr(dna_string_t, rna_string_u)
  end

  private

  def dna_string_t
    'T'
  end

  def rna_string_u
    'U'
  end
end
