class DNA

  attr_accessor :sequence

  def initialize(dna_string)
    self.sequence = dna_string
  end

  def to_rna
    transcribe(sequence)
  end

  private

  def transcribe(sequence)
    sequence.gsub(/T/,"U")
  end
end
