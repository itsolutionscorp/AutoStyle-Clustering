class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    transcribe_thymidine_to_uracil
  end

  private

  THYMIDINE = 'T'
  URACIL = 'U'

  def transcribe_thymidine_to_uracil
    @sequence.gsub(THYMIDINE, THYMIDINE)
  end
end
