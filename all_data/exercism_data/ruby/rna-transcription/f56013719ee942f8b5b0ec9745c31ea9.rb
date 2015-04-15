class DNA
  attr_reader :dna_sequence

  def initialize(sequence)
    @dna_sequence = sequence
  end

  def to_rna
    dna_sequence.tr('T','U')
  end
end
