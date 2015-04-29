class DNA
  def initialize(dna_string)
    @dna_string = dna_string
  end

  def to_rna
    transcribe_thymidine_to_uracil(@dna_string)
  end

  private

  def transcribe_thymidine_to_uracil(dna_string)
    dna_string.gsub('T', 'U')
  end
end
