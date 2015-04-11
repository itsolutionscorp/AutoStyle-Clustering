class DNA

  def initialize(strand)
    @strain = strand
  end

  def to_rna
    @strain.tr(differing_dna_alphabet, corresponding_rna_alphabet)
  end

  private
  def differing_dna_alphabet
    'T'
  end
  def corresponding_rna_alphabet
    'U'
  end
end
