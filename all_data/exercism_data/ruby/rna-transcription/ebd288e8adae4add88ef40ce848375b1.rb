class DNA

  def initialize(strand)
    @strain = strand
  end

  def to_rna
    @strain.tr(dna_alphabet_to_transcribe, corresponding_rna_alphabet)
  end

  private
  def dna_alphabet_to_transcribe
    'T'
  end
  def corresponding_rna_alphabet
    'U'
  end
end
