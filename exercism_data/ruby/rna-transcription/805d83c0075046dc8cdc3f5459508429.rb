class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  attr_reader :sequence

  def to_rna
    sequence.tr("CGAT", "CGAU")  # Fore!
  end
end
