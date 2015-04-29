class DNA
  NUCLEOTIDES = %w[A C G T U]

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

  def count(nucleotide)
    if invalid_nucleotide?(nucleotide)
      raise ArgumentError, "'#{nucleotide}' is not a valid nucleotide"
    end

    sequence.count(nucleotide)
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
    sequence.delete(NUCLEOTIDES.join).empty?
  end

  def invalid?
    !valid?
  end

  def valid_nucleotide?(nucleotide)
    NUCLEOTIDES.include?(nucleotide)
  end

  def invalid_nucleotide?(nucleotide)
    !valid_nucleotide?(nucleotide)
  end
end
