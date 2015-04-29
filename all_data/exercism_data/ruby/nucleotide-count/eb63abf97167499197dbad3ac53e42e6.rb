class DNA
  PROTEINS = %w[A C G T U]

  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence

    if contains_uracil?
      raise ArgumentError, "sequence must not contain uracil"
    end

    if invalid?
      raise ArgumentError, "not a valid DNA sequence"
    end
  end

  def count(protein)
    if invalid_protein?(protein)
      raise ArgumentError, "'#{protein}' is not a valid protein"
    end

    sequence.count(protein)
  end

  def nucleotide_counts
    { 'A' => count('A'),
      'T' => count('T'),
      'C' => count('C'),
      'G' => count('G') }
  end

  private

  def contains_uracil?
    sequence.include?('U')
  end

  def valid?
    sequence.delete(PROTEINS.join).empty?
  end

  def invalid?
    !valid?
  end

  def valid_protein?(protein)
    PROTEINS.include?(protein)
  end

  def invalid_protein?(protein)
    !valid_protein?(protein)
  end
end
