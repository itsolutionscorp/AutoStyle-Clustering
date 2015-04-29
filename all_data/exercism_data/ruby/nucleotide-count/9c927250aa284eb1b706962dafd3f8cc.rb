class DNA
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence.chars
    validate_sequence
  end

  def count(nucleotide)
    raise ArgumentError unless nucleotides.include?(nucleotide)
    sequence.count(nucleotide)
  end

  def nucleotide_counts
    sequence.each_with_object(default_counts) do |nucleotide, result|
      result[nucleotide] += 1
    end
  end

  def default_counts
    {
      'A' => 0,
      'T' => 0,
      'C' => 0,
      'G' => 0
    }.clone
  end

  def nucleotides
    default_counts.keys
  end

  def validate_sequence
    raise ArgumentError if (sequence - nucleotides).any?
  end
end
