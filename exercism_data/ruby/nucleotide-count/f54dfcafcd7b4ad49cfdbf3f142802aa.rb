class DNA
  def initialize dna
    validate_arguments(dna)
    @nucleotides = build_nucleotide_frequency dna
  end

  def count nucleotide
    validate_arguments nucleotide
    @nucleotides.fetch(nucleotide, 0)
  end

  def nucleotide_counts
    @nucleotides.to_hash
  end

  private

  def validate_arguments unsequenced_dna
    raise(ArgumentError, 'Strands should only contain ACGT') \
      unless nucleic_sequence?(unsequenced_dna)
  end

  def build_nucleotide_frequency dna
    dna.chars.each_with_object(initialize_nucleotides) do
      |nucleotide, nucleotide_frequency|
      nucleotide_frequency[nucleotide] += 1
    end
  end

  def initialize_nucleotides
    {
    'A' => 0,
    'T' => 0,
    'C' => 0,
    'G' => 0,
    }
  end

  def nucleic_sequence? unsequenced_dna
    unsequenced_dna.scan(/[ACGT]/).count == unsequenced_dna.size
  end
end
